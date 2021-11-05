package com.hxl.cooldeploy.service.impl

import com.hxl.cooldeploy.git.event.PushEvent
import com.hxl.cooldeploy.service.IProjectService
import com.hxl.cooldeploy.utils.DirectoryUtils
import com.hxl.cooldeploy.git.util.GitUtils
import org.springframework.stereotype.Service

@Service
class ProjectServiceImpl : IProjectService {
    var log = org.slf4j.LoggerFactory.getLogger(ProjectServiceImpl::class.java)
    override fun listProject(): List<String> {
        return DirectoryUtils.listProjects()
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
        log.info("close目{}", url)
        var name = url.substring(url.lastIndexOf("/") + 1).removeSuffix(".git")
        if (DirectoryUtils.projectIsExist(name)){
            return pullProject(name)
        }
        return GitUtils.clone(url, name);
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