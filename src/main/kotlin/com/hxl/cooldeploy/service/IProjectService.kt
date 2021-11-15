package com.hxl.cooldeploy.service

import com.hxl.cooldeploy.bean.ProjectBean
import com.hxl.cooldeploy.git.event.PushEvent
import com.hxl.cooldeploy.vo.ProjectConfigVO
import org.springframework.scheduling.annotation.Async
import java.util.concurrent.Future

interface IProjectService {
    @Async
    fun cloneProject(sshUrl: String, dir: String): Future<Boolean>

    @Async
    fun pullProject(dir: String): Future<Boolean>

    @Async
    fun execTask(project: String, taskName: String)

    @Async
    fun execProjectCommand(name: String): String

    @Async
    fun execProjectShell(name: String): String

    @Async
    fun buildAndDeploy(name: String)

    fun autoBuildProject(event: PushEvent): Boolean
    fun listProject(): List<ProjectBean>
    fun build(projectBean: ProjectBean)
    fun saveConfig(body: ProjectConfigVO): String
    fun listTasks(projectName: String): Any

}