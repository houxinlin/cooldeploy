package com.hxl.cooldeploy.kotlin.extent

import com.fasterxml.jackson.databind.ObjectMapper

class MapExtent

fun <K, V> Map<out K, V>.toJsonString(): String {
    return ObjectMapper().writeValueAsString(this);
}