package com.hxl.cooldeploy.utils

import com.hxl.cooldeploy.bean.ProjectBean

class ShellKeyword {

    companion object {
        fun generatorMap(item: ProjectBean): MutableMap<String, String> {
            return mutableMapOf<String, String>().apply {
                put("Project_HOME", item.projectPath)
                System.getenv().forEach { (t, u) -> put(t, u) }
            }
        }
    }

    fun generatorNewString(old: String, projectBean: ProjectBean): String {
        var newString = old;
        for (key in projectBean.evn.keys) {
            println(key + "  " + projectBean.evn.get(key))
            newString = newString.replace("{Project_HOME}", projectBean.evn[key]!!)
        }
        println(newString)
        return newString;
    }

    private fun createKeywordMap(key: String, value: String): KeywordMap {
        return KeywordMap(key, value)
    }
}