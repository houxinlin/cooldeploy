package com.hxl.cooldeploy.config

import com.hxl.cooldeploy.kotlin.extent.toFile
import com.hxl.cooldeploy.kotlin.extent.toJsonString
import com.hxl.cooldeploy.kotlin.extent.toMap
import com.hxl.cooldeploy.utils.DirectoryUtils
import com.hxl.cooldeploy.utils.FileUtils
import java.util.*

class SystemDefaultConfig : Properties() {
    init {
        putIfAbsent("login_passwd", "cooldeploy")
    }

    fun init() {
        DirectoryUtils.initNecessaryFile()
        this.putAll(DirectoryUtils.getSystemConfigFile().toFile().toMap())
        FileUtils.writeString(DirectoryUtils.getSystemConfigFile(), this.toJsonString())
    }

    override fun put(key: Any?, value: Any?): Any? {
        System.setProperty(key as String, value as String)
        return super.put(key, value)
    }

    fun storage() {
        var mutableMapOf = mutableMapOf<String, String>()
        this.keys.forEach { mutableMapOf[it.toString()] = System.getProperty(it.toString()) }
        FileUtils.writeString(DirectoryUtils.getSystemConfigFile(), mutableMapOf.toJsonString())
    }
}