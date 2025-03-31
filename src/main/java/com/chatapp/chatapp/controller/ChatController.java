package com.chatapp.chatapp.controller;
import com.chatapp.chatapp.model.Chatmessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
private SimpMessagingTemplate simpMessagingTemplate;

public  ChatController(SimpMessagingTemplate simpMessagingTemplate){
    this.simpMessagingTemplate = simpMessagingTemplate;
}


@MessageMapping("/message")
    public void sendMessage(Chatmessage chatmessage){
    simpMessagingTemplate.convertAndSend("/topics/messages", chatmessage);
}
}
