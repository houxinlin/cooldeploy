package com.hxl.cooldeploy.build.impl

import com.hxl.cooldeploy.bean.ProjectBean
import com.hxl.cooldeploy.build.Build
import com.hxl.cooldeploy.kotlin.extent.toFile
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
    override fun build(projectName: ProjectBean) {
        var newConnector = GradleConnector.newConnector()
        var connect = getConnector(projectName.projectPath)
        var projectModel = connect.model(GradleProject::class.java)
    }

    companion object {
        fun execTask(projectName: String, taskName: String) {
            var connector = getConnector(projectName)
            var action = connector.action()
            action.build()
                .setStandardOutput(System.out)
                .forTasks(taskName).addArguments()
                .run()
        }

        fun listTasks(projectName: String): List<String> {
            var connector = getConnector(projectName)
            var projectModel = connector.model(GradleProject::class.java)
            return projectModel.get().tasks.stream().map {
                it.name
            }.toList()
        }

        fun getConnector(projectName: String): ProjectConnection {
            var newConnector = GradleConnector.newConnector()
            return newConnector
                .forProjectDirectory(projectName.toFile())
                .connect()
        }
    }

}
