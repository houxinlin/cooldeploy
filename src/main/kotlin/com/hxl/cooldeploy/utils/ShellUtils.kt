package com.hxl.cooldeploy.utils

import com.hxl.cooldeploy.build.ProjectBuild
import java.io.BufferedInputStream
import java.io.File

import java.io.IOException


object ShellUtils {
    var log = org.slf4j.LoggerFactory.getLogger(ShellUtils::class.java)
    fun runScript(path: String): String {
        log.info("执行构建脚本{},是否存在{}", path, File(path).exists())
        if (!DirectoryUtils.isExist(path)) {
            return "文件不存在"
        }
        return if (File(path).canExecute()) {
            val processBuilder = ProcessBuilder(path)
            processBuilder.inheritIO()
            val process = processBuilder.start()
            process.waitFor()
            return process.inputStream.bufferedReader().readText();
        } else {
            log.info("文件无无权限执行");
            return "文件无全新执行"

        }

    }
}