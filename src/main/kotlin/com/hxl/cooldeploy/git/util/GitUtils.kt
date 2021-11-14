package com.hxl.cooldeploy.git.util

import com.hxl.cooldeploy.kotlin.extent.toFile
import com.hxl.cooldeploy.websocket.WebSocketSessionStorage
import org.eclipse.jgit.api.CloneCommand
import org.eclipse.jgit.api.Git
import org.eclipse.jgit.api.TransportConfigCallback
import org.eclipse.jgit.lib.AnyObjectId
import org.eclipse.jgit.transport.*
import java.io.File


class GitUtils {
    companion object {
        fun pull(dir: String): Boolean {
            try {
                var pull = Git.open(dir.toFile())
                    .pull().setTransportConfigCallback { transport ->
                        val sshTransport = transport as SshTransport
                        sshTransport.sshSessionFactory = SshSessionFactory()
                    }.call()
                WebSocketSessionStorage.sendMessageToAll("${dir} pull 完成\n")
                return pull.isSuccessful
            } catch (e: Exception) {
                WebSocketSessionStorage.sendMessageToAll(e.localizedMessage + "\n")
            }
            return false;
        }

        fun gitLog(dir: String): String {
            return try {
                var first = Git.open(dir.toFile()).log().call().first()
                first.name;
            } catch (e: Exception) {
                "";
            }
        }

        fun clone(url: String, dir: String): Boolean {
            try {
                WebSocketSessionStorage.sendMessageToAll("clone项目${url}->${dir}\n")
                Git.cloneRepository()
                    .setURI(url)
                    .setDirectory(dir.toFile())
                    .setTransportConfigCallback { transport ->
                        val sshTransport = transport as SshTransport
                        sshTransport.sshSessionFactory = SshSessionFactory()
                    }.call()
                WebSocketSessionStorage.sendMessageToAll("clone项目完毕\n")
            } catch (e: Exception) {
                WebSocketSessionStorage.sendMessageToAll(e.localizedMessage + "\n")
            }
            return true;
        }
    }
}