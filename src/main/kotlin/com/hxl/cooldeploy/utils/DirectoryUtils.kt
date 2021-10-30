package com.hxl.cooldeploy.utils

import org.eclipse.jgit.util.FileUtils
import java.io.File
import java.nio.file.FileSystems
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.io.path.absolutePathString

class DirectoryUtils {
    companion object {
        private const val WORK_DIR_NAME = "work"
        private const val PROJECT_DIR_NAME = "projects"
        private const val SHELL_DIR_NAME = "shells"

        /**
         * 获取工作路径
         */
        fun getWorkPath(): String {
            return FileSystems.getDefault().getPath(WORK_DIR_NAME).absolutePathString()
        }

        /**
         * 是否存在
         */
        fun isExist(name: String): Boolean {
            return Files.exists(Paths.get(name))
        }

        /**
         * 删除文件夹
         */
        fun deleteDirectory(name: String) {
            File(getWorkPath(), name).deleteRecursively()
        }

        /**
         * 获取项目完整路径
         */
        fun getProjectPath(name: String): String {
            return Paths.get(getWorkPath(), PROJECT_DIR_NAME, name).toString()
        }

        /**
         * 项目是否存在
         */
        fun projectIsExist(name: String): Boolean {
            return isExist(getProjectPath(name))
        }
    }
}