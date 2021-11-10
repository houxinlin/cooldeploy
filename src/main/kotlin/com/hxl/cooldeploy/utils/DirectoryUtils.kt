package com.hxl.cooldeploy.utils

import org.eclipse.jgit.util.FileUtils
import java.io.File
import java.nio.file.FileSystems
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.stream.Stream
import kotlin.io.path.absolutePathString
import kotlin.streams.toList

class DirectoryUtils {
    companion object {
        private const val WORK_DIR_NAME = "work"
        private const val PROJECT_DIR_NAME = "projects"
        private const val COMMAND_DIR_NAME = "commands"
        private const val SHELL_DIR_NAME = "shells"

        fun getProjectCommandStorageDir(name: String): String {
            return Paths.get(getChildDir(COMMAND_DIR_NAME), name, "index.data").toString()
        }

        fun getProjectShellStorageDir(name: String): String {
            return Paths.get(getChildDir(SHELL_DIR_NAME), name, "index.sh").toString()
        }

        fun getChildDir(name: String): String {
            return Paths.get(getWorkPath(), name).toString();
        }

        fun listProjects(): List<String> {
            return Files.list(Paths.get(getWorkPath(), PROJECT_DIR_NAME)).map { it.last().toString() }.toList()
        }

        /**
         * 获取工作路径
         */
        fun getWorkPath(): String {
            return FileSystems.getDefault().getPath(WORK_DIR_NAME).absolutePathString()
        }

        /**
         * 是否存在
         */
        fun isExist(fullPath: String): Boolean {
            return Files.exists(Paths.get(fullPath))
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
        fun getProjectPath(projectName: String): String {
            return Paths.get(getWorkPath(), PROJECT_DIR_NAME, projectName).toString()
        }

        /**
         * 项目是否存在
         */
        fun projectIsExist(projectName: String): Boolean {
            return isExist(getProjectPath(projectName))
        }

        fun hasProject(name: String): Boolean {
            return projectIsExist(name)
        }
    }
}