package com.hxl.cooldeploy.utils

import com.jcraft.jsch.JSch
import com.jcraft.jsch.Session
import org.eclipse.jgit.api.Git
import org.eclipse.jgit.api.TransportConfigCallback
import org.eclipse.jgit.transport.*
import org.eclipse.jgit.util.FS
import java.io.File


class GitUtils {
    companion object {
        fun clone(url: String, dir: String) {
            val sshSessionFactory: SshSessionFactory = object : JschConfigSessionFactory() {
                override fun configure(host: OpenSshConfig.Host?, session: Session) {
                }
                override fun createDefaultJSch(fs: FS?): JSch {
                    val defaultJSch = super.createDefaultJSch(fs)
                    defaultJSch.removeAllIdentity()
                    defaultJSch.addIdentity("/home/hxl/.ssh/id_rsa","passphrase");
                    return defaultJSch
                }
            }
            Git.cloneRepository()
                .setURI(url)
                .setDirectory(File(dir))
                .setTransportConfigCallback(object :TransportConfigCallback{
                    override fun configure(transport: Transport?) {
                        val sshTransport = transport as SshTransport
                        sshTransport.sshSessionFactory = sshSessionFactory
                    }
                })
                .call()
        }
    }
}