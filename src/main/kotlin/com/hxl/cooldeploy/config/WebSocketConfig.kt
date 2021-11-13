package com.hxl.cooldeploy.config

import com.hxl.cooldeploy.websocket.WebSocketHandlerImpl
import com.hxl.cooldeploy.websocket.WebSocketInterceptorImpl
import org.springframework.context.annotation.Configuration
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.WebSocketMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.config.annotation.*
import org.springframework.web.socket.handler.TextWebSocketHandler
import org.springframework.web.socket.server.HandshakeInterceptor
import java.lang.Exception


@Configuration
@EnableWebSocket
class WebSocketConfig:WebSocketConfigurer {
    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry
            .addHandler(WebSocketHandlerImpl(),"wbs")
            .addInterceptors(WebSocketInterceptorImpl())
            .setAllowedOrigins("*")
    }


}