package com.hxl.cooldeploy.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.hxl.cooldeploy.utils.ResultUtils
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import java.lang.Exception
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class UserInterceptor : HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        var login = request.session.getAttribute("login")
        if (login == null || login == false) {
            response.contentType = "text/paint;charset=UTF-8"
            response.writer.append(createResponseBody())
            response.writer.flush()
            return false
        }
        return true
    }

    private fun createResponseBody(): String {
        var objectMapper = ObjectMapper()
        return objectMapper.writeValueAsString(ResultUtils.success("/login", 302))
    }


}