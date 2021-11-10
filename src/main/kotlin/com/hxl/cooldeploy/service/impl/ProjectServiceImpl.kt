package com.hxl.cooldeploy.service.impl

import com.hxl.cooldeploy.bean.ProjectBean
import com.hxl.cooldeploy.build.BuildToolEnum
import com.hxl.cooldeploy.build.ProjectBuild
import com.hxl.cooldeploy.build.impl.Gradle
import com.hxl.cooldeploy.git.event.PushEvent
import com.hxl.cooldeploy.service.IProjectService
import com.hxl.cooldeploy.utils.DirectoryUtils
import com.hxl.cooldeploy.git.util.GitUtils
import com.hxl.cooldeploy.kotlin.extent.toArrayList
import com.hxl.cooldeploy.kotlin.extent.toFile
import com.hxl.cooldeploy.kotlin.extent.toStringJson
import com.hxl.cooldeploy.utils.FileUtils
import com.hxl.cooldeploy.vo.ProjectConfigVO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import java.nio.file.Paths

@Service
class ProjectServiceImpl : IProjectService {
    var log = org.slf4j.LoggerFactory.getLogger(ProjectServiceImpl::class.java)


    @Async
    override fun execTask(projectName: String, taskName: String) {
        if (listProject().find { it.projectName == projectName }?.buildTool == BuildToolEnum.GRADLE) {
            return Gradle.execTask(DirectoryUtils.getProjectPath(projectName), taskName)
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
        if (DirectoryUtils.hasProject(body.projectName)) {
            //保存command
            FileUtils.writeString(
                DirectoryUtils.getProjectCommandStorageDir(body.projectName),
                body.projectCommands.toStringJson()
            )
            //保存shell
            FileUtils.writeString(DirectoryUtils.getProjectShellStorageDir(body.projectName), body.shell)
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
            applyProjectProperty(item,projectBean)
            projectList.add(projectBean)
        }
        return projectList;
    }

    fun applyProjectProperty(path:String,item: ProjectBean) {
        item.apply {
            shell=FileUtils.readString(DirectoryUtils.getProjectShellStorageDir(path))
            buildCommands=DirectoryUtils.getProjectCommandStorageDir(path).toFile().toArrayList()
            firstCommitId = GitUtils.gitLog(DirectoryUtils.getProjectPath(path))
            projectPath = path
            projectName = path
            buildTool = projectBuild.getProjectBuildTool(projectPath)
        }
    }

    override fun cloneProject(sshUrl: String, dir: String): Boolean {
        log.info("clone项目{}", sshUrl)
        return GitUtils.clone(sshUrl, dir)
    }

    override fun pullProject(dir: String): Boolean {
        log.info("pull项目{}", dir)
        return GitUtils.pull(DirectoryUtils.getProjectPath(dir))
    }


    override fun cloneProject(url: String): Boolean {
        var name = url.substring(url.lastIndexOf("/") + 1).removeSuffix(".git")
        if (DirectoryUtils.projectIsExist(name)) {
            return pullProject(name)
        }
        log.info("close项目{},名称{}", url, name)

        return GitUtils.clone(url, DirectoryUtils.getProjectPath(name));
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