package com.hxl.cooldeploy.build.impl

import com.hxl.cooldeploy.bean.ProjectBean
import com.hxl.cooldeploy.build.Build
import com.hxl.cooldeploy.kotlin.extent.toArrayList
import com.hxl.cooldeploy.kotlin.extent.toFile
import com.hxl.cooldeploy.utils.DirectoryUtils
import org.gradle.StartParameter
import org.gradle.tooling.GradleConnector
import org.gradle.tooling.ProjectConnection
import org.gradle.tooling.model.*
import org.gradle.tooling.model.build.BuildEnvironment
import org.gradle.tooling.model.eclipse.EclipseProject
import org.gradle.tooling.model.gradle.GradleBuild
import org.gradle.tooling.model.gradle.GradlePublication
import org.gradle.tooling.model.gradle.ProjectPublications
import org.gradle.tooling.model.idea.IdeaProject
import kotlin.streams.toList

class Gradle : Build {
    /**
     * 构建
     */
    override fun build(projectName: ProjectBean) {
        var commands =
            DirectoryUtils.getProjectCommandStorageDir(projectName.projectName).toFile().toArrayList()
        var listTasks = listTasks(projectName.projectPath)
        for (command in commands) {
            if (listTasks.contains(command)) {
                execTask(projectName.projectPath, command)
            } else {
                println("${command} 不存在")
            }
        }

//        execTask(projectName.projectPath,)
    }

    companion object {
        /**
         * 执行Gradle Task
         */
        fun execTask(projectPath: String, taskName: String) {
            var connector = getConnector(projectPath)
            var action = connector.action()
            action.build()
                .setStandardOutput(System.out)
                .forTasks(taskName).addArguments()
                .run()
        }

        /**
         * 列举Gradle Task
         */

        fun listTasks(projectName: String): List<String> {
            var connector = getConnector(projectName)
            var projectModel = connector.model(GradleProject::class.java)
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
                .forProjectDirectory(projectName.toFile())
                .connect()
        }
    }

}
