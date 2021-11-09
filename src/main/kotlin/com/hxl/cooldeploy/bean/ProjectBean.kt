package com.hxl.cooldeploy.bean

import com.hxl.cooldeploy.build.BuildToolEnum
import jdk.nashorn.tools.Shell

/**
 * 项目信息相关
 */
data class ProjectBean(
    var buildCommands: List<String>,
    var shell: String,
    var firstCommitId: String,
    var projectName: String,
    var projectPath: String,
    var buildTool: BuildToolEnum,
    var packageList: MutableList<String>
) {
    constructor() : this(
        mutableListOf(),
        "",
        "",
        "",
        "",
        BuildToolEnum.NONE,
        mutableListOf()
    )
}