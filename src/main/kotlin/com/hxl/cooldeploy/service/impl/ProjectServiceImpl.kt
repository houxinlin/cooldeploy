package com.hxl.cooldeploy.service.impl

import com.hxl.cooldeploy.event.PushEvent
import com.hxl.cooldeploy.service.IProjectService
import com.hxl.cooldeploy.utils.DirectoryUtils
import com.hxl.cooldeploy.utils.GitUtils
import com.hxl.cooldeploy.utils.ShellUtils
import org.eclipse.jgit.api.Git
import org.springframework.stereotype.Service

@Service
class ProjectServiceImpl : IProjectService {
    var log = org.slf4j.LoggerFactory.getLogger(ProjectServiceImpl::class.java)
    override fun cloneProject(event: PushEvent): Boolean {
        log.info("clone项目{}", event.repository!!.name)
        return GitUtils.clone(event.repository!!.ssh_url!!, DirectoryUtils.getProjectPath(event.repository!!.name!!))
    }

    override fun pullProject(event: PushEvent): Boolean {
        log.info("pull项目{}", event.repository!!.name)
        return GitUtils.pull(
            DirectoryUtils.getProjectPath(event.repository!!.name!!)
        )
    }

    override fun getProject(event: PushEvent): Boolean {
        /**
         * 如果项目不存在，则clone,存在则pull
         */
        return if (DirectoryUtils.projectIsExist(event.repository!!.name!!)) {
            pullProject(event)
        } else {
            cloneProject(event)
        }

    }
}