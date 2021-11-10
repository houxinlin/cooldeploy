package com.hxl.cooldeploy.kotlin.extent

class ListExtent {
}

fun <T> MutableList<T>.toStringJson(): String {
    var result = StringBuffer("")
    var iterator = this.iterator()
    result.append("[")
    while (iterator.hasNext()) {
        result.append("\"${iterator.next()}\"")
        result.append(",")
    }
    result.deleteCharAt(result.length - 1)
    result.append("]")
    return result.toString();
}