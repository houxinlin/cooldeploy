package com.hxl.cooldeploy.service

import com.hxl.cooldeploy.event.PushEvent

interface IProjectService {
    fun cloneProject(event: PushEvent): Boolean;

    fun pullProject(event: PushEvent): Boolean;

    fun getProject(event: PushEvent): Boolean;


}