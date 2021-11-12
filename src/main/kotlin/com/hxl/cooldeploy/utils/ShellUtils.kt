package com.hxl.cooldeploy.utils

import com.hxl.cooldeploy.build.ProjectBuild
import com.hxl.cooldeploy.kotlin.extent.toFile
import com.hxl.cooldeploy.websocket.WebSocketSessionStorage
import java.io.BufferedInputStream
import java.io.File

import java.io.IOException


object ShellUtils {
    var log = org.slf4j.LoggerFactory.getLogger(ShellUtils::class.java)
    fun runScript(path: String): String {
        var exists = path.toFile().exists()
        WebSocketSessionStorage.sendMessageToAll("执行项目脚本${path}\n")
        log.info("执行构建脚本{},是否存在{}", path, path.toFile().exists())
        if (!exists) {
            WebSocketSessionStorage.sendMessageToAll("项目脚本${path} 不存在\n")
            return "文件不存在"
        }

        return if (File(path).canExecute()) {
            val processBuilder = ProcessBuilder(path)

            val process = processBuilder.start()
            process.waitFor()
            var readText = process.inputStream.bufferedReader().readText()

            WebSocketSessionStorage.sendMessageToAll(readText)
            "OK"
        } else {
            WebSocketSessionStorage.sendMessageToAll("项目脚本${path} 文件无权限执行\n")
            log.info("文件无权限执行");
            "文件无权限执行"
        }

    }
}