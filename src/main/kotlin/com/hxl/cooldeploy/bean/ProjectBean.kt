package com.hxl.cooldeploy.bean

import com.hxl.cooldeploy.build.BuildToolEnum

data class ProjectBean(
    var projectName: String,
    var projectPath: String,
    var buildTool: BuildToolEnum,
    var packageList: MutableList<String>
) {
    constructor() : this("",
        "",
        BuildToolEnum.NONE,
        mutableListOf())
}