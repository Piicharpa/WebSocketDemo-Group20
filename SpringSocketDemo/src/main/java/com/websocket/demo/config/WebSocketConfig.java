package com.example.Chatdemo.confic;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration //websocket is one of springboots config.
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override //กำหนด path
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("*")//เปิดรับrequestจากทุกที่
                .withSockJS();
    }

    @Override //กำหนดการรับส่งข้อมูลว่ารับ or boardcast ผ่านทางไหน
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app")//server receive from
                .enableSimpleBroker("/topic");// server broadcast to
    }


}
