package com.example.Chatdemo.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessageSendingOperations messagingTemplate;
    private final Counter count ;

    @MessageMapping("/chat.addUser")//cleint ควรใช้ endpoint ไหนในการส่งข้อความให้ server
    @SendTo("/topic/public")//ควรส่งให้ client ในช่องทางไหน
    public ChatMessage addUser(ChatMessage message, SimpMessageHeaderAccessor headerAccessor){
        headerAccessor.getSessionAttributes().put("username",message.getSender());
        messagingTemplate.convertAndSend("/topic/Onlineuser",count.countup());

        return message;
    }
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage addUser(ChatMessage message){
        return message;
    }


}
