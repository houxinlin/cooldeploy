package com.hxl.cooldeploy.config

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.type.TypeFactory
import com.hxl.cooldeploy.resolver.JsonObjectValue
import org.springframework.context.annotation.Configuration
import org.springframework.core.MethodParameter
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import javax.servlet.http.HttpServletRequest


@Configuration
class WebConfig : WebMvcConfigurer {
    override fun addViewControllers(registry: ViewControllerRegistry) {
        super.addViewControllers(registry)
//        registry.addViewController("/").setViewName("index")
//        registry.addViewController("/project").setViewName("project")
    }

    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        super.addArgumentResolvers(resolvers)
        resolvers.add(object : HandlerMethodArgumentResolver {
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
                var body = (webRequest.nativeRequest as HttpServletRequest).inputStream.bufferedReader().readLine()
                val mapper = ObjectMapper()
                val o: HashMap<String?, Any?>? = mapper.readValue(body, mapType)
                return o?.get(key);
            }
        })
    }
}