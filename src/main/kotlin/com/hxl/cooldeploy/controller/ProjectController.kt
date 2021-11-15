package com.hxl.cooldeploy.controller

import com.hxl.cooldeploy.resolver.JsonObjectValue
import com.hxl.cooldeploy.service.IProjectService
import com.hxl.cooldeploy.utils.DirectoryUtils
import com.hxl.cooldeploy.utils.ResultUtils
import com.hxl.cooldeploy.vo.ProjectConfigVO
import org.gradle.tooling.GradleConnector
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.io.File

@RestController
@RequestMapping("api/project")
class ProjectController {

    @Autowired
    lateinit var projectService: IProjectService;


    @GetMapping("listProject")
    fun listProject(): Any {
        return ResultUtils.success(projectService.listProject(), 0)
    }

    @PostMapping("cloneProject")
    fun cloneProject(@JsonObjectValue("address") url: String): String {
        var name = url.substring(url.lastIndexOf("/") + 1).removeSuffix(".git")
        if (DirectoryUtils.projectIsExist(name)) {
            pullProject(name)
            return "OK"
        }
        projectService.cloneProject(url, DirectoryUtils.getProjectPath(name)).get();
        return "OK"
    }

    @GetMapping("listTasks")
    fun listTasks(@RequestParam("name") name: String): Any {
        return ResultUtils.success(projectService.listTasks(name), 0)
    }

    @PostMapping("saveConfig")
    fun saveConfig(@RequestBody body: ProjectConfigVO): Any {
        return ResultUtils.success(projectService.saveConfig(body), 0)
    }

    @PostMapping("gradleTask")
    fun execTask(
        @JsonObjectValue("projectName") project: String,
        @JsonObjectValue("taskName") taskName: String
    ): Any {
        ResultUtils.success(projectService.execTask(project, taskName), 0)
        return "OK";
    }

    @GetMapping("execProjectCommand")
    fun execProjectCommand(@RequestParam("projectName") name: String): Any {
        ResultUtils.success(projectService.execProjectCommand(name), 0)
        return "OK"
    }

    @GetMapping("execProjectShell")
    fun execProjectShell(@RequestParam("projectName") name: String): Any {
        return ResultUtils.success(projectService.execProjectShell(name), 0)
    }

    @GetMapping("buildAndDeploy")
    fun buildAndDeploy(@RequestParam("projectName") name: String): Any {
        return ResultUtils.success(projectService.buildAndDeploy(name), 0)
    }

    @GetMapping("pullProject")
    fun pullProject(@RequestParam("projectName") name: String): Any {
        projectService.pullProject(name)
        return ResultUtils.success("OK", 0)
    }
}