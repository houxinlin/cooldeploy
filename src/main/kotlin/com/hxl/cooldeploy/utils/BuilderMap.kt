package com.hxl.cooldeploy.utils

class BuilderMap : HashMap<String, Any>() {
    override fun put(key: String, value: Any): BuilderMap {
        super.put(key, value)
        return this;
    }
}