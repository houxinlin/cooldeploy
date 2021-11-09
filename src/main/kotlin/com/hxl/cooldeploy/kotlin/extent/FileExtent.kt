package com.hxl.cooldeploy.kotlin.extent

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.type.TypeFactory
import com.fasterxml.jackson.module.kotlin.readValue
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
    var objectMapper = ObjectMapper()
    var type = TypeFactory.defaultInstance().constructArrayType(String::class.java)
    var list: Array<String> = objectMapper.readValue<Array<String>>(this, type)
    return list.toList()
}