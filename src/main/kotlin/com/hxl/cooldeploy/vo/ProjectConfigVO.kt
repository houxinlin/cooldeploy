package com.hxl.cooldeploy.vo

class ProjectConfigVO {
    var projectName: String = "";

    var projectCommands: MutableList<String> = mutableListOf<String>()

    var shell: String = "";
    override fun toString(): String {
        return "ProjectConfigVO(projectName='$projectName', projectCommands=$projectCommands, shell='$shell')"
    }


}