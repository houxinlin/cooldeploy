package com.hxl.cooldeploy.controller

import com.hxl.cooldeploy.resolver.JsonObjectValue
import com.hxl.cooldeploy.service.IProjectService
import com.hxl.cooldeploy.utils.ResultUtils
import com.hxl.cooldeploy.vo.ProjectConfigVO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

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
    fun cloneProject(@JsonObjectValue("address") address: String?): String {
        address?.let {
            var project = projectService.cloneProject(address)

            println("${project}")

        }
        return "a"
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
        return ResultUtils.success(projectService.execTask(project, taskName), 0)
    }
}