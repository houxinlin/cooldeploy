package com.hxl.cooldeploy.service.impl

import com.hxl.cooldeploy.bean.ProjectBean
import com.hxl.cooldeploy.build.BuildToolEnum
import com.hxl.cooldeploy.build.ProjectBuild
import com.hxl.cooldeploy.build.impl.Gradle
import com.hxl.cooldeploy.git.event.PushEvent
import com.hxl.cooldeploy.service.IProjectService
import com.hxl.cooldeploy.git.util.GitUtils
import com.hxl.cooldeploy.kotlin.extent.toArrayList
import com.hxl.cooldeploy.kotlin.extent.toFile
import com.hxl.cooldeploy.kotlin.extent.toStringJson
import com.hxl.cooldeploy.utils.*
import com.hxl.cooldeploy.vo.ProjectConfigVO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class ProjectServiceImpl : IProjectService {
    var log = org.slf4j.LoggerFactory.getLogger(ProjectServiceImpl::class.java)

    @Async
    override fun buildAndDeploy(name: String) {
        execProjectCommand(name);
        execProjectShell(name)
    }

    @Async
    override fun execProjectShell(name: String): String {
        listProject().find { it.projectName == name }
            ?.let { ShellUtils.runScript(DirectoryUtils.getProjectShellStorageDir(it!!.projectName)) }
        return "OK";
    }

    @Async
    override fun execProjectCommand(name: String): String {
        listProject().find { it.projectName == name }?.let { build(it!!) }
        return "OK"
    }

    @Async
    override fun execTask(projectName: String, taskName: String) {
        if (listProject().find { it.projectName == projectName }?.buildTool == BuildToolEnum.GRADLE) {
            Gradle.execTask(DirectoryUtils.getProjectPath(projectName), taskName)
        }
    }

    override fun listTasks(projectName: String): Any {
        if (listProject().find { it.projectName == projectName }?.buildTool == BuildToolEnum.GRADLE) {
            return Gradle.listTasks(DirectoryUtils.getProjectPath(projectName))
        }
        return emptyList<String>()
    }

    override fun saveConfig(body: ProjectConfigVO): String {
        /**
         * 项目是否存在
         */
        listProject().find { it.projectName == body.projectName }?.let {
            //保存command
            ProjectFileUtils.setProjectBuildCommand(body.projectName, body.projectCommands.toStringJson())
            ProjectFileUtils.setProjectBuildShell(body.projectName, ShellKeyword().generatorNewString(body.shell, it))
            return "保存成功";
        }
        return "没有此项目"
    }

    @Autowired
    lateinit var projectBuild: ProjectBuild;

    override fun build(projectBean: ProjectBean) {
        var newInstance = projectBean.buildTool.buildClass.java.newInstance()
        newInstance::class.java.getDeclaredMethod("build", ProjectBean::class.java).invoke(newInstance, projectBean)
    }

    override fun listProject(): List<ProjectBean> {
        var projectsPathList = DirectoryUtils.listProjects()
        var projectList = mutableListOf<ProjectBean>()
        for (item in projectsPathList) {
            var projectBean = ProjectBean()
            applyProjectProperty(item, projectBean)
            projectList.add(projectBean)
        }
        return projectList;
    }

    fun applyProjectProperty(path: String, item: ProjectBean) {
        item.apply {
            shell = FileUtils.readString(DirectoryUtils.getProjectShellStorageDir(path), "#!/bin/sh\n")
            buildCommands = DirectoryUtils.getProjectCommandStorageDir(path).toFile().toArrayList()
            firstCommitId = GitUtils.gitLog(DirectoryUtils.getProjectPath(path))
            projectPath = DirectoryUtils.getProjectPath(path)
            projectName = path
            buildTool = projectBuild.getProjectBuildTool(projectPath)
            evn = ShellKeyword.generatorMap(item)
        }
    }

    override fun cloneProject(sshUrl: String, dir: String): Boolean {
        log.info("clone项目{}", sshUrl)
        return GitUtils.clone(sshUrl, dir)
    }


    override fun pullProject(dir: String): Boolean {
        log.info("pull项目{}", DirectoryUtils.getProjectPath(dir))
        return GitUtils.pull(DirectoryUtils.getProjectPath(dir))
    }


    @Async
    override fun cloneProject(url: String) {
        var name = url.substring(url.lastIndexOf("/") + 1).removeSuffix(".git")
        if (DirectoryUtils.projectIsExist(name)) {
            pullProject(name)
        }
        log.info("close项目{},名称{}", url, name)
        cloneProject(url, DirectoryUtils.getProjectPath(name));
    }

    override fun getProject(event: PushEvent): Boolean {
        /**
         * 如果项目不存在，则clone,存在则pull
         */
        return if (DirectoryUtils.projectIsExist(event.repository!!.name!!)) {
            pullProject(event.repository!!.name!!)
        } else {
            cloneProject(
                DirectoryUtils.getProjectPath(event.repository!!.ssh_url!!),
                DirectoryUtils.getProjectPath(event.repository!!.name!!)
            )
        }

    }
}