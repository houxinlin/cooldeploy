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
            var pull = Git.open(dir.toFile()).pull().call()
            return pull.isSuccessful
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
            WebSocketSessionStorage.sendMessageToAll("clone项目${url}->${dir}\n")
            Git.cloneRepository()
                .setURI(url)
                .setDirectory(dir.toFile())
                .setTransportConfigCallback { transport ->
                    val sshTransport = transport as SshTransport
                    sshTransport.sshSessionFactory = SshSessionFactory()
                }.setCallback(object : CloneCommand.Callback {
                    override fun initializedSubmodules(submodules: MutableCollection<String>?) {
                    }

                    override fun cloningSubmodule(path: String?) {
                    }

                    override fun checkingOut(commit: AnyObjectId?, path: String?) {
                    }
                })
                .call()
            WebSocketSessionStorage.sendMessageToAll("clone项目完毕\n")
            return true;
        }
    }
}