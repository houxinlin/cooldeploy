package com.hxl.cooldeploy.build.impl

import com.hxl.cooldeploy.bean.ProjectBean
import com.hxl.cooldeploy.build.Build
import com.hxl.cooldeploy.kotlin.extent.toArrayList
import com.hxl.cooldeploy.kotlin.extent.toFile
import com.hxl.cooldeploy.utils.DirectoryUtils
import com.hxl.cooldeploy.utils.ShellUtils
import com.hxl.cooldeploy.websocket.WebSocketSessionStorage

class Npm : Build {
    override fun build(projectName: ProjectBean) {
        var commands = DirectoryUtils.getProjectCommandStorageDir(projectName.projectName).toFile().toArrayList()
        WebSocketSessionStorage.sendMessageToAll("执行npm命令${commands}\n")
        for (command in commands) {
            var buildCommand = ShellUtils.buildCommand(command)
            WebSocketSessionStorage.sendMessageToAll(
                ShellUtils.runCommand(
                    projectName.projectPath,
                    buildCommand
                ) + "\n"
            )
        }
    }
}