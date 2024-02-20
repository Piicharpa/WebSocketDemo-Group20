package com.example.Chatdemo.confic;

import com.example.Chatdemo.chat.ChatMessage;
import com.example.Chatdemo.chat.Counter;
import com.example.Chatdemo.chat.MessageType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@RequiredArgsConstructor
public class WebSocketEventListener {
    private final Counter count ;
    private final SimpMessageSendingOperations messagingTemplate;
    @EventListener//ดัก event connect disconnect
    public void disconnect(SessionDisconnectEvent event){
        SimpMessageHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");

        if(username != null){
            var chatMessage = ChatMessage.builder()
                    .sender(username)
                    .type(MessageType.LEAVE)
                    .build();

            messagingTemplate.convertAndSend("/topic/Onlineuser",count.countdown());
            messagingTemplate.convertAndSend("/topic/public",chatMessage);
        }
    }
}
