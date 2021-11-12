package com.hxl.cooldeploy.build.impl

import com.hxl.cooldeploy.bean.ProjectBean
import com.hxl.cooldeploy.build.Build
import com.hxl.cooldeploy.kotlin.extent.toArrayList
import com.hxl.cooldeploy.kotlin.extent.toFile
import com.hxl.cooldeploy.utils.DirectoryUtils
import com.hxl.cooldeploy.websocket.WebSocketSessionStorage
import org.apache.maven.shared.invoker.DefaultInvocationRequest
import org.apache.maven.shared.invoker.DefaultInvoker
import org.apache.maven.shared.invoker.InvocationRequest
import org.apache.maven.shared.invoker.Invoker
import java.nio.file.Paths
import java.util.*


class Maven : Build {
    override fun build(projectName: ProjectBean) {
        val request: InvocationRequest = DefaultInvocationRequest()

        request.pomFile = Paths.get(projectName.projectPath, "pom.xml").toFile()
        var arrayList = DirectoryUtils.getProjectCommandStorageDir(projectName.projectName).toFile().toArrayList()
        request.goals = arrayList
        val invoker: Invoker = DefaultInvoker()
        var mavenHome = System.getenv("MAVEN_HOME")
        invoker.setOutputHandler {
            WebSocketSessionStorage.sendMessageToAll(it + "\n")
        }
        mavenHome?.let {
            var path = Paths.get(it)
            if (path.last().toString() == "bin") {
                invoker.mavenHome = "/${path.subpath(0, path.count() - 1)}".toFile()
            }
            invoker.execute(request)
            return
        }
        WebSocketSessionStorage.sendMessageToAll("Maven环境不存在，请添加MAVEN_HOME到系统环境变量中\n")
    }
}