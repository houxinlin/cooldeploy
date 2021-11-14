package com.hxl.cooldeploy.build

import com.hxl.cooldeploy.build.impl.Gradle
import com.hxl.cooldeploy.build.impl.Maven
import com.hxl.cooldeploy.build.impl.None
import com.hxl.cooldeploy.build.impl.Npm
import kotlin.reflect.KClass

enum class BuildToolEnum(
    val buildClass: KClass<*>,
    val toolName: String,
    val featuresFileName: List<String>
) {
    GRADLE(Gradle::class, "gradle", mutableListOf("gradlew", "gradle/wrapper/gradle-wrapper.jar")),
    MAVEN(Maven::class,"maven", mutableListOf("pom.xml")),
    NPM(Npm::class,"npm", mutableListOf("package.json")),
    NONE(None::class,"none", mutableListOf());
    companion object {
        @JvmStatic
        fun listTools(): MutableList<BuildToolEnum> {
            return mutableListOf(BuildToolEnum.GRADLE, BuildToolEnum.MAVEN, BuildToolEnum.NPM)
        }
    }
}
