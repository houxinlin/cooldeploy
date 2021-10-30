package com.hxl.cooldeploy.build

import com.hxl.cooldeploy.utils.DirectoryUtils
import com.hxl.cooldeploy.utils.ShellUtils
import org.springframework.stereotype.Component
import java.nio.file.Paths

@Component
class ProjectBuild {
    var log = org.slf4j.LoggerFactory.getLogger(ProjectBuild::class.java)
    fun buildProject(name: String): String {
        log.info("构建项目{}", name)
        ShellUtils.runScript(Paths.get(DirectoryUtils.getWorkPath(), "shells", "$name.sh").toString())
        log.info("构建项目{}完成", name)
        return "OK"
    }

}