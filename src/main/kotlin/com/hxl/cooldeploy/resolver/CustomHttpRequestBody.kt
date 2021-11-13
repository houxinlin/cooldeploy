package com.hxl.cooldeploy.resolver

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletRequestWrapper
import kotlin.Throws
import java.io.IOException
import javax.servlet.ServletInputStream
import java.io.ByteArrayInputStream
import javax.servlet.ReadListener
import java.util.stream.Collectors

class CustomHttpRequestBody(request: HttpServletRequest) : HttpServletRequestWrapper(request) {
    private val body: String

    @Throws(IOException::class)
    override fun getInputStream(): ServletInputStream {
        val byteArrayInputStream = ByteArrayInputStream(body.toByteArray())
        return object : ServletInputStream() {
            @Throws(IOException::class)
            override fun read(): Int {
                return byteArrayInputStream.read()
            }

            override fun isFinished(): Boolean {
                return false
            }

            override fun isReady(): Boolean {
                return false
            }

            override fun setReadListener(listener: ReadListener) {
            }
        }
    }

    init {
        body = request.reader.lines().collect(Collectors.joining())
    }
}