package com.hxl.cooldeploy.build

import com.hxl.cooldeploy.kotlin.extent.hasChild
import com.hxl.cooldeploy.kotlin.extent.toFile
import com.hxl.cooldeploy.utils.DirectoryUtils
import com.hxl.cooldeploy.utils.ShellUtils
import org.springframework.stereotype.Component
import java.nio.file.Paths

@Component
class ProjectBuild {
    var log = org.slf4j.LoggerFactory.getLogger(ProjectBuild::class.java)


    fun getProjectBuildTool(project: String): BuildToolEnum {
        var listTools = BuildToolEnum.listTools()
        for (item in listTools) {
            var featuresFileName = item.featuresFileName
            if (project.toFile().hasChild(*featuresFileName.toTypedArray())){
                return item;
            }
        }
        return BuildToolEnum.NONE
    }

}