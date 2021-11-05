package com.hxl.cooldeploy.git.util

import org.eclipse.jgit.api.Git
import org.eclipse.jgit.api.TransportConfigCallback
import org.eclipse.jgit.transport.*
import java.io.File


class GitUtils {
    companion object {
        fun pull(dir: String): Boolean {
            var pull = Git.open(File(dir)).pull().call()
            return pull.isSuccessful
        }

        fun clone(url: String, dir: String): Boolean {
            Git.cloneRepository()
                .setURI(url)
                .setDirectory(File(dir))
                .setTransportConfigCallback(object : TransportConfigCallback {
                    override fun configure(transport: Transport?) {
                        val sshTransport = transport as SshTransport
                        sshTransport.sshSessionFactory = SshSessionFactory()
                    }
                })
                .call()
            return true;
        }
    }
}