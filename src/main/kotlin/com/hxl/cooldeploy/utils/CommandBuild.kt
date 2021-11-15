package com.hxl.cooldeploy.utils

class CommandBuild() {
    private var list = mutableListOf<String>();

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