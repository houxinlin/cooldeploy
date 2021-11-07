package com.hxl.cooldeploy

import org.springframework.boot.SpringApplicationRunListener
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.ServletComponentScan
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.mvc.method.RequestMappingInfo
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping


@SpringBootApplication
@ServletComponentScan
@EnableAsync
class CoolDeployApplication : SpringApplicationRunListener {

}

fun main(args: Array<String>) {
    var application = runApplication<CoolDeployApplication>(*args)

}
