package com.hxl.cooldeploy.utils

import com.hxl.cooldeploy.bean.ProjectBean

class ShellKeyword {

    companion object {
        fun generatorMap(item: ProjectBean): MutableMap<String, String> {
            return mutableMapOf<String, String>().apply {
                put("Project_Home", item.projectPath)
                System.getenv().forEach { (t, u) -> put(t, u) }
            }
        }
    }

    fun generatorNewString(old: String, projectBean: ProjectBean): String {
        var newString = old;
        for (key in projectBean.evn.keys) {
            newString = newString.replace("{${key}}", projectBean.evn[key]!!)
        }
        return newString;
    }

    private fun createKeywordMap(key: String, value: String): KeywordMap {
        return KeywordMap(key, value)
    }
}