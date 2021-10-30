package com.hxl.cooldeploy

import com.hxl.cooldeploy.utils.GitUtils
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.io.File

@SpringBootApplication
class CoolDeployApplication

fun main(args: Array<String>) {
    runApplication<CoolDeployApplication>(*args)
}
