package com.example.Chatdemo.chat;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChatMessage {
    private String content;
    private String sender;
    private String timestamp;
    private MessageType type;
}
