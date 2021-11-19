package com.hxl.cooldeploy.utils

import com.hxl.cooldeploy.build.ProjectBuild
import com.hxl.cooldeploy.kotlin.extent.toFile
import com.hxl.cooldeploy.websocket.WebSocketSessionStorage
import org.eclipse.jgit.internal.storage.reftree.Command
import java.io.BufferedInputStream
import java.io.File

import java.io.IOException


object ShellUtils {
    var log = org.slf4j.LoggerFactory.getLogger(ShellUtils::class.java)
    fun buildCommand( command: String): List<String> {
        return CommandBuild().addCommand(command).get();
    }

    fun runCommand(env: String, command: List<String>): String {
        log.info(command.toString())
        var start = ProcessBuilder().directory(env.toFile())
            .command(command).start()
        start.waitFor()
        var exitValue = start.exitValue()
        println(exitValue)
        if (exitValue != 0) {
            return start.errorStream.bufferedReader().readText()
        }
        var readText = start.inputStream.bufferedReader().readText()
        return readText
    }

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
            WebSocketSessionStorage.sendMessageToAll("脚本执行结果\n")
            WebSocketSessionStorage.sendMessageToAll(readText)
            log.info("脚本执行结束${readText}")
            "OK"
        } else {
            WebSocketSessionStorage.sendMessageToAll("项目脚本${path} 文件无权限执行\n")
            log.info("文件无权限执行");
            "文件无权限执行"
        }

    }
}