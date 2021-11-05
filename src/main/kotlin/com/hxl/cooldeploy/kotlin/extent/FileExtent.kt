package com.hxl.cooldeploy.kotlin.extent

import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

class FileExtent {
}

fun File.hasChild(vararg names: String): Boolean {
    if (names.isEmpty()){return false}
    for (name in names) {
        if (!Files.exists(Paths.get(this.toString(), name))) {
            return false;
        }
    }
    return true;
}