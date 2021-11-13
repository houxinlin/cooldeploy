package com.hxl.cooldeploy.git.util

import com.hxl.cooldeploy.controller.SystemController
import com.jcraft.jsch.JSch
import com.jcraft.jsch.Session
import org.eclipse.jgit.transport.JschConfigSessionFactory
import org.eclipse.jgit.transport.OpenSshConfig
import org.eclipse.jgit.util.FS

class SshSessionFactory : JschConfigSessionFactory() {
    override fun configure(host: OpenSshConfig.Host?, session: Session) {
        session.setConfig("StrictHostKeyChecking", "no")
    }

    override fun createDefaultJSch(fs: FS?): JSch {
        val defaultJSch = super.createDefaultJSch(fs)
        defaultJSch.removeAllIdentity()
        defaultJSch.addIdentity("~/.ssh/${SystemController.SSH_NAME}", "passphrase");
        return defaultJSch
    }
}