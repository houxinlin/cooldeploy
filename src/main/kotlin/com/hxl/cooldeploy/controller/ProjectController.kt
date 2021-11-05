package com.hxl.cooldeploy.controller

import com.hxl.cooldeploy.build.ProjectBuild
import com.hxl.cooldeploy.resolver.JsonObjectValue
import com.hxl.cooldeploy.service.IProjectService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import java.net.URL

@RestController
@RequestMapping("project")
class ProjectController {

    @Autowired
    lateinit var projectService: IProjectService;


    @Autowired
    lateinit var projectBuild: ProjectBuild;
    @GetMapping("listProject")
    fun listProject() {
        var listProject = projectService.listProject()
        for (s in listProject) {
            println(projectBuild.getProjectBuildTool(s))
        }
        println(listProject)
    }

    @PostMapping("cloneProject")
    fun cloneProject(@JsonObjectValue("address") address: String?): String {
        address?.let {
            projectService.cloneProject(address)

        }
        return "a"
    }
}