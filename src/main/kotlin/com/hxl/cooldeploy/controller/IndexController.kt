package com.hxl.cooldeploy.controller

import com.alibaba.fastjson.JSON
import com.hxl.cooldeploy.build.ProjectBuild
import com.hxl.cooldeploy.git.event.PushEvent
import com.hxl.cooldeploy.resolver.JsonObjectValue
import com.hxl.cooldeploy.service.IProjectService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("webhook")
class IndexController {

    @Autowired
    lateinit var build: ProjectBuild;

    @Autowired
    lateinit var projectService: IProjectService;

    @PostMapping("test")
    fun test(
        @JsonObjectValue("a") map: String,
        @JsonObjectValue("b") map2: String): String {
        println(map)
        return "";
    }

    @PostMapping("/")
    fun index(@RequestBody body: String): Any {
        if ((Regex("\"head_commit\"").containsMatchIn(body))) {
            var push = JSON.parseObject(body, PushEvent::class.java)
            projectService.getProject(push)
            return projectService.buildAndDeploy(push.repository!!.name!!)
        }
        return "";
    }
}