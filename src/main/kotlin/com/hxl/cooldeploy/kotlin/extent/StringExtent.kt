package com.hxl.cooldeploy.kotlin.extent

import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

class StringExtent {
}

fun String.toFile(): File {
    return File(this)
}


fun String.createFile():Boolean{
    Files.createFile(Paths.get(this))
    return true
}

