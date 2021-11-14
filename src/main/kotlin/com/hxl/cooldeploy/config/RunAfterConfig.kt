package com.hxl.cooldeploy.config

import com.hxl.cooldeploy.utils.DirectoryUtils
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Configuration

@Configuration
class RunAfterConfig : CommandLineRunner {
    override fun run(vararg args: String?) {
        SystemDefaultConfig().init()
        Thread.UncaughtExceptionHandler { t, e ->  }
        Thread.setDefaultUncaughtExceptionHandler { t, e ->
            println("aaaa")
        }
    }
}