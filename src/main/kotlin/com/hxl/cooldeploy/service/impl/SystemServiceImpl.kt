package com.hxl.cooldeploy.service.impl

import com.hxl.cooldeploy.config.SystemDefaultConfig
import com.hxl.cooldeploy.service.ISystemService
import com.hxl.cooldeploy.utils.DirectoryUtils
import org.springframework.stereotype.Service

@Service
class SystemServiceImpl : ISystemService {
    override fun login(passwd: String): Boolean {
        return System.getProperty("login_passwd").equals(passwd)
    }

    override fun configPasswd(passwd: String): String {
        System.setProperty("login_passwd", passwd)
        SystemDefaultConfig().storage()
        return passwd;
    }
}