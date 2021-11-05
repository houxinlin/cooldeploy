package com.hxl.cooldeploy.service

import com.hxl.cooldeploy.git.event.PushEvent

interface IProjectService {
    fun cloneProject(sshUrl: String,dir:String): Boolean;

    fun pullProject(dir:String): Boolean;

    fun getProject(event: PushEvent): Boolean;

    fun cloneProject(url: String): Boolean;

    fun listProject(): List<String>


}