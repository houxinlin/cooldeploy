package com.hxl.cooldeploy.utils

import com.hxl.cooldeploy.kotlin.extent.createFile
import com.hxl.cooldeploy.kotlin.extent.toFile
import java.nio.file.Files
import java.nio.file.Paths

object FileUtils {
    fun writeString(str: String, body: String) {
        var file = str.toFile()
        file.parentFile.mkdirs()
        Files.write(Paths.get(str), body.toByteArray())
    }
}