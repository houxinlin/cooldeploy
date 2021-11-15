package com.hxl.cooldeploy.config

import com.hxl.cooldeploy.kotlin.extent.toFile
import com.hxl.cooldeploy.utils.DirectoryUtils
import org.gradle.tooling.GradleConnector
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Configuration
import java.io.File

@Configuration
class RunAfterConfig : CommandLineRunner {
    override fun run(vararg args: String?) {


        SystemDefaultConfig().init()

    }
}