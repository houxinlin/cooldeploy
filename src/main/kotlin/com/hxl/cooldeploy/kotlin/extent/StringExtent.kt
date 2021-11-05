package com.hxl.cooldeploy.kotlin.extent

import java.io.File

class StringExtent {
}

fun String.toFile(): File {
    return File(this)
}
