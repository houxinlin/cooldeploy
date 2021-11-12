package com.hxl.cooldeploy.service

import com.hxl.cooldeploy.bean.ProjectBean
import com.hxl.cooldeploy.git.event.PushEvent
import com.hxl.cooldeploy.vo.ProjectConfigVO

interface IProjectService {
    fun cloneProject(sshUrl: String, dir: String): Boolean;
    fun pullProject(dir: String): Boolean;
    fun getProject(event: PushEvent): Boolean;
    fun cloneProject(url: String): Boolean;
    fun listProject(): List<ProjectBean>
    fun build(projectBean: ProjectBean)
    fun saveConfig(body: ProjectConfigVO): String
    fun listTasks(projectName: String): Any
    fun execTask(project: String, taskName: String)
    fun execProjectCommand(name: String): String
    fun execProjectShell(name: String): String
    fun buildAndDeploy(name: String)

}