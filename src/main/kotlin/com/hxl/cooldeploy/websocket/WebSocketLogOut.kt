package com.hxl.cooldeploy.websocket

import java.io.ByteArrayOutputStream
import kotlin.Throws
import java.io.IOException
import java.io.OutputStream

class WebSocketLogOut : OutputStream() {
    @Throws(IOException::class)
    override fun write(b: Int) {
    }

    override fun write(b: ByteArray, off: Int, len: Int) {
        var byteArrayOutputStream = ByteArrayOutputStream()
        byteArrayOutputStream.write(b, off, len)
        WebSocketSessionStorage.sendMessageToAll(byteArrayOutputStream.toByteArray().decodeToString())
    }

}