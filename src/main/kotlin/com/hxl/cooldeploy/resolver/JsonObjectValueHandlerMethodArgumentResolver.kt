package com.hxl.cooldeploy.resolver

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.type.TypeFactory
import org.springframework.core.MethodParameter
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import org.springframework.web.util.ContentCachingRequestWrapper
import javax.servlet.http.HttpServletRequest

class JsonObjectValueHandlerMethodArgumentResolver : HandlerMethodArgumentResolver {
    var threadLocal = ThreadLocal<String>();
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.hasParameterAnnotation(JsonObjectValue::class.java)
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any? {
        var key = parameter.getParameterAnnotation(JsonObjectValue::class.java)!!.key
        var mapType = TypeFactory.defaultInstance()
            .constructMapType(Map::class.java, String::class.java, String::class.java)
        var requestWrapper = (webRequest.nativeRequest as HttpServletRequest);
        val mapper = ObjectMapper()
        var body = if (threadLocal.get() != null) {
            threadLocal.get()
        } else {
            requestWrapper.inputStream.bufferedReader().readText();
        }
        threadLocal.set(body)
        val o: HashMap<String?, Any?>? = mapper.readValue(body!!, mapType)
        return o?.get(key);
    }
}