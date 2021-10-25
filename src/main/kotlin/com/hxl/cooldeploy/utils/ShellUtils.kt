package com.hxl.cooldeploy.utils

import java.io.BufferedInputStream

import java.io.IOException


class ShellUtils {
    companion object {
        @Throws(IOException::class, InterruptedException::class)
        fun runScript(path: String) {
            println(path)
            if (!FileUtils.isExist(path)) {
                return
            }
            val processBuilder = ProcessBuilder(path)
            processBuilder.inheritIO()
            val process = processBuilder.start()
            val exitValue = process.waitFor()
            if (exitValue != 0) {
                // check for errors
                BufferedInputStream(process.errorStream)
                throw RuntimeException("execution of script failed!")
            }
        }
    }
}