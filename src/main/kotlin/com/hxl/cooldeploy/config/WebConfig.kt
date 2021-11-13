package com.hxl.cooldeploy.config

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.type.TypeFactory
import com.hxl.cooldeploy.resolver.JsonObjectValue
import com.hxl.cooldeploy.resolver.JsonObjectValueHandlerMethodArgumentResolver
import org.springframework.context.annotation.Configuration
import org.springframework.core.MethodParameter
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
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
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(UserInterceptor())
            .addPathPatterns("/**")
            .excludePathPatterns("/api/system/login")
    }

    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        super.addArgumentResolvers(resolvers)
        resolvers.add(JsonObjectValueHandlerMethodArgumentResolver())
    }
}