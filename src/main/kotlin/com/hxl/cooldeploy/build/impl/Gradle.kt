package com.hxl.cooldeploy.build.impl

import com.hxl.cooldeploy.bean.ProjectBean
import com.hxl.cooldeploy.build.Build
import com.hxl.cooldeploy.kotlin.extent.toArrayList
import com.hxl.cooldeploy.kotlin.extent.toFile
import com.hxl.cooldeploy.service.impl.ProjectServiceImpl
import com.hxl.cooldeploy.utils.DirectoryUtils
import com.hxl.cooldeploy.websocket.WebSocketLogOut
import com.hxl.cooldeploy.websocket.WebSocketSessionStorage
import org.gradle.StartParameter
import org.gradle.tooling.GradleConnector
import org.gradle.tooling.ProjectConnection
import org.gradle.tooling.ResultHandler
import org.gradle.tooling.internal.consumer.connection.InternalPhasedActionAdapter
import org.gradle.tooling.model.*
import org.gradle.tooling.model.build.BuildEnvironment
import org.gradle.tooling.model.eclipse.EclipseProject
import org.gradle.tooling.model.gradle.GradleBuild
import org.gradle.tooling.model.gradle.GradlePublication
import org.gradle.tooling.model.gradle.ProjectPublications
import org.gradle.tooling.model.idea.IdeaProject
import java.nio.file.Paths
import kotlin.streams.toList

class Gradle : Build {

    /**
     * 构建
     */
    override fun build(projectName: ProjectBean) {
        var commands = DirectoryUtils.getProjectCommandStorageDir(projectName.projectName).toFile().toArrayList()
        log.info("执行build${commands}")
        WebSocketSessionStorage.sendMessageToAll("执行gradle命令${commands}\n")
        var listTasks = listTasks(projectName.projectPath)
        for (command in commands) {
            if (listTasks.contains(command)) {
                execTask(projectName.projectPath, command)
            } else {
                WebSocketSessionStorage.sendMessageToAll("Task: $command 不存在\n")
            }
        }
        WebSocketSessionStorage.sendMessageToAll("gradle命令执行完成${commands}\n")
    }

    companion object {
        var log = org.slf4j.LoggerFactory.getLogger(Gradle::class.java)

        /**
         * 执行Gradle Task
         */
        fun execTask(projectPath: String, taskName: String) {


            var connector = getConnector(projectPath)
            connector.newBuild()
                .setStandardOutput(WebSocketLogOut())
                .forTasks(taskName)
                .run()
        }

        /**
         * 列举Gradle Task
         */

        fun listTasks(projectName: String): List<String> {
            var connector = getConnector(projectName)
            var projectModel = connector.model(GradleProject::class.java)
            projectModel.setStandardOutput(WebSocketLogOut())
            return projectModel.get().tasks.stream().map {
                it.name
            }.toList()
        }

        /**
         * 获取项目的Connector
         */
        fun getConnector(projectName: String): ProjectConnection {
            var newConnector = GradleConnector.newConnector()
            return newConnector
                .useBuildDistribution()
                .forProjectDirectory(projectName.toFile())
                .connect()
        }
    }

}
