package com.hxl.cooldeploy.kotlin.extent

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.type.TypeFactory
import com.fasterxml.jackson.module.kotlin.readValue
import com.hxl.cooldeploy.resolver.CustomHttpRequestBody
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

class FileExtent {
}

fun File.hasChild(vararg names: String): Boolean {
    if (names.isEmpty()) {
        return false
    }
    for (name in names) {
        if (!Files.exists(Paths.get(this.toString(), name))) {
            return false;
        }
    }
    return true;
}

fun File.toArrayList(): List<String> {
    try {
        var objectMapper = ObjectMapper()
        var type = TypeFactory.defaultInstance().constructArrayType(String::class.java)
        var list: Array<String> = objectMapper.readValue<Array<String>>(this, type)
        return list.toList()

    } catch (e: Exception) {
        return emptyList();
    }
}

fun File.toMap(): MutableMap<String, String> {
    var mapType = TypeFactory.defaultInstance()
        .constructMapType(Map::class.java, String::class.java, String::class.java)
    val mapper = ObjectMapper()
    var readText = this.readText()
    if (readText.isBlank()) {
        return mutableMapOf<String, String>();
    }
   return   mapper.readValue(this, mapType)
}