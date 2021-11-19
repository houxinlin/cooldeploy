package com.hxl.cooldeploy.utils

import java.text.MessageFormat

object Command {
    const val SSH_KEY: String = "ssh-keygen -m PEM -b 2048 -f  {0} -q -N \"\"";
    fun format(pattern: String, vararg arg: String): String {
        return MessageFormat.format(pattern, arg)
    }
}