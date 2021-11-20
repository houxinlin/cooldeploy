package com.hxl.cooldeploy.controller

import com.hxl.cooldeploy.resolver.JsonObjectValue
import com.hxl.cooldeploy.service.ISystemService
import com.hxl.cooldeploy.utils.Command
import com.hxl.cooldeploy.utils.CommandBuild
import com.hxl.cooldeploy.utils.ResultUtils
import com.hxl.cooldeploy.utils.ShellUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.nio.file.Paths
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("api/system")
class SystemController {
    companion object {
        var SSH_NAME: String = "CoolDeploy"
    }

    @Autowired
    lateinit var iSystemService: ISystemService

    @PostMapping("login")
    fun login(
        @JsonObjectValue("passwd") password: String,
        httpServletRequest: HttpServletRequest
    ): Any {
        var result = iSystemService.login(password)
        httpServletRequest.session.setAttribute("login", result)
        return ResultUtils.success(result, 0);
    }

    @PostMapping("configLoginPasswd")
    fun configLoginPasswd(
        @JsonObjectValue("passwd") password: String,
        httpServletRequest: HttpServletRequest
    ): Any {

        return ResultUtils.success(iSystemService.configPasswd(password), 0);
    }

    @GetMapping("getSystemConfig")
    fun getSystemConfig(): Any {
        return ResultUtils.success(System.getProperties(), 0);
    }

    @GetMapping("getRsaPub")
    fun getRsaPub(): Any {
        var path = Paths.get(System.getenv("HOME"), ".ssh", "${SSH_NAME}.pub").toFile()
        if (path.exists()) {
            return ResultUtils.success(path.readText(), 0);
        }
        return ResultUtils.success("", 0);
    }

    @GetMapping("generatorRsa")
    fun generator(): Any {
        var path = Paths.get(System.getenv("HOME"), ".ssh", "${SSH_NAME}")
        println(path)
        var result = ShellUtils.runCommand(
            System.getProperty("user.home"),
            CommandBuild().addCommand(CommandBuild.format(Command.SSH_KEY, path.toString())).get()
        )
        return ResultUtils.success("${result}", 0);
    }
}