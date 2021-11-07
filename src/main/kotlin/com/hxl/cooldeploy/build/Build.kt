package com.hxl.cooldeploy.build

import com.hxl.cooldeploy.bean.ProjectBean

interface Build {
    fun build(projectName: ProjectBean)
}