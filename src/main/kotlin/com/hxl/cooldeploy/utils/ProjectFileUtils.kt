package com.hxl.cooldeploy.utils

import com.hxl.cooldeploy.kotlin.extent.toStringJson

object ProjectFileUtils {
    fun setProjectBuildCommand(name: String, value: String) {
        FileUtils.writeString(DirectoryUtils.getProjectCommandStorageDir(name), value).setExecutable(true)
    }

    fun setProjectBuildShell(name: String, value: String) {
        FileUtils.writeString(DirectoryUtils.getProjectShellStorageDir(name), value).setExecutable(true)
    }
}