package com.hxl.cooldeploy.service

interface ISystemService {
    fun login(passwd: String): Boolean
    fun configPasswd(passwd: String):String
}