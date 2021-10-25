package com.hxl.cooldeploy.controller

import com.alibaba.fastjson.JSON
import com.hxl.cooldeploy.build.ProjectBuild
import com.hxl.cooldeploy.event.PushEvent
import com.hxl.cooldeploy.utils.FileUtils
import com.hxl.cooldeploy.utils.GitUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("")
class IndexController {

    @Autowired
    lateinit var build: ProjectBuild;

    @PostMapping("/")
    fun index(@RequestBody body: String): Any {
        if ((Regex("\"head_commit\"").containsMatchIn(body))) {
            var push = JSON.parseObject(body, PushEvent::class.java)
            FileUtils.deleteProject(push.repository!!.name!!)
            GitUtils.clone(push.repository!!.ssh_url!!, FileUtils.getProjectPath(push.repository!!.name!!))
            build.buildProject(push.repository!!.name!!);
        }
        return "";
    }
}