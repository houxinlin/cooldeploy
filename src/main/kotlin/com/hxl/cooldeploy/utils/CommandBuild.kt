package com.hxl.cooldeploy.utils

import java.text.MessageFormat

class CommandBuild() {
    private var list = mutableListOf<String>();

    companion object {
        fun format(pattern: String, arg:Any): String {
            return MessageFormat.format(pattern,arg)
        }
    }

    init {
        list.add("bash")
        list.add("-c")
    }

    fun addCommand(command: String): CommandBuild {
        list.add(command);
        return this;
    }

    fun get(): List<String> {
        return list;
    }
}