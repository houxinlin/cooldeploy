package com.hxl.cooldeploy.config

import com.hxl.cooldeploy.controller.SystemController
import com.hxl.cooldeploy.kotlin.extent.toFile
import com.hxl.cooldeploy.kotlin.extent.toJsonString
import com.hxl.cooldeploy.kotlin.extent.toMap
import com.hxl.cooldeploy.utils.DirectoryUtils
import com.hxl.cooldeploy.utils.FileUtils
import java.util.*

class SystemDefaultConfig : Properties() {
    init {
        //初始化必要目录
        DirectoryUtils.initNecessaryFile()
        var localMap = DirectoryUtils.getSystemConfigFile().toFile().toMap()
        if (localMap.isEmpty()) {
            default()
            storage()
        }
    }

    fun default(): SystemDefaultConfig {
        put("login_passwd", "cooldeploy")
        return this;
    }

    fun init() {

        //从本地文件加载配置
        this.putAll(DirectoryUtils.getSystemConfigFile().toFile().toMap())

    }

    override fun put(key: Any?, value: Any?): Any? {
        //把属性都存如系统值
        System.setProperty(key as String, value as String)
        return super.put(key, value)
    }

    /**
     * 存储
     */
    fun storage() {
        var mutableMapOf = mutableMapOf<String, String>()
        //从系统的属性中拿值。
        this.keys.forEach { mutableMapOf[it.toString()] = System.getProperty(it.toString()) }
        FileUtils.writeString(DirectoryUtils.getSystemConfigFile(), mutableMapOf.toJsonString())
    }
}