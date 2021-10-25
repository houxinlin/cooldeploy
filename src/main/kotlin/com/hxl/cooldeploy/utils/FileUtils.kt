package com.hxl.cooldeploy.utils

import org.eclipse.jgit.util.FileUtils
import java.io.File
import java.nio.file.FileSystems
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.io.path.absolutePathString

class FileUtils {
    companion object {
        fun getWorkPath(): String {
            var absolutePathString = FileSystems.getDefault().getPath("project").absolutePathString()
            return absolutePathString
        }

        fun isExist(name: String): Boolean {
            return Files.exists(Paths.get(name))
        }

        fun deleteProject(name: String) {
            File(getWorkPath(), name).deleteRecursively()
        }

        fun getProjectPath(name: String): String {
            return File(getWorkPath(), name).toString();
        }
    }
}