package com.hxl.cooldeploy.controller

import com.alibaba.fastjson.JSON
import com.hxl.cooldeploy.build.ProjectBuild
import com.hxl.cooldeploy.event.PushEvent
import com.hxl.cooldeploy.service.IProjectService
import com.hxl.cooldeploy.utils.DirectoryUtils
import com.hxl.cooldeploy.utils.GitUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("")
class IndexController {

    @Autowired
    lateinit var build: ProjectBuild;

    @Autowired
    lateinit var projectService: IProjectService;

    @PostMapping("/")
    fun index(@RequestBody body: String): Any {
        if ((Regex("\"head_commit\"").containsMatchIn(body))) {
            var push = JSON.parseObject(body, PushEvent::class.java)
            projectService.getProject(push)
            return build.buildProject(push.repository!!.name!!)
        }
        return "";
    }
}