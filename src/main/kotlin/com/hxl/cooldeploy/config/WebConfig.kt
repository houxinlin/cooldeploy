package com.hxl.cooldeploy.config

import com.hxl.cooldeploy.resolver.JsonObjectValueHandlerMethodArgumentResolver
import org.springframework.boot.web.server.ErrorPage
import org.springframework.boot.web.server.WebServerFactoryCustomizer
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
class WebConfig : WebMvcConfigurer {
    override fun addViewControllers(registry: ViewControllerRegistry) {
        super.addViewControllers(registry)
    }

    @Bean
    fun webServerFactoryCustomizer(): WebServerFactoryCustomizer<*>? {
        return WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> { factory ->
            val error404Page = ErrorPage(HttpStatus.NOT_FOUND, "/index.html")
            factory.addErrorPages(error404Page)
        }
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(UserInterceptor())
            .addPathPatterns("/api/**")
            .excludePathPatterns("/api/system/login")
    }

    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        super.addArgumentResolvers(resolvers)
        resolvers.add(JsonObjectValueHandlerMethodArgumentResolver())
    }
}