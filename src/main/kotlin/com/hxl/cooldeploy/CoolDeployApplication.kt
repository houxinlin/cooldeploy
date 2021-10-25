package com.hxl.cooldeploy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CoolDeployApplication

fun main(args: Array<String>) {
    runApplication<CoolDeployApplication>(*args)
}
