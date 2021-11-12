package com.hxl.cooldeploy.websocket

import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import java.util.concurrent.ConcurrentHashMap

object WebSocketSessionStorage {
    var map = ConcurrentHashMap<String, WebSocketSession>();

    fun sendMessageToAll(message: String) {
        for (session in map.values) {
            session.sendMessage(TextMessage(message))
        }
    }
}