package com.hxl.cooldeploy.build

enum class BuildToolEnum(val toolName: String, val featuresFileName: List<String>) {
    GRADLE("gradle", mutableListOf()),
    MAVEN("maven", mutableListOf()),
    NPM("npm", mutableListOf("package.json")),
    NONE("none", mutableListOf());

    companion object{
        @JvmStatic
        fun listTools(): MutableList<BuildToolEnum> {
            return mutableListOf(BuildToolEnum.GRADLE, BuildToolEnum.MAVEN, BuildToolEnum.NPM)
        }
    }
}
