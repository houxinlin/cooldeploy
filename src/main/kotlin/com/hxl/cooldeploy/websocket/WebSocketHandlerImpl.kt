package com.hxl.cooldeploy.websocket

import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

class WebSocketHandlerImpl : TextWebSocketHandler() {
    override fun afterConnectionEstablished(session: WebSocketSession) {
        WebSocketSessionStorage.map[session.id] = session;
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        WebSocketSessionStorage.map.remove(session.id);
    }
}