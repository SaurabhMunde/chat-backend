package com.chatapp.chatapp.controller;
import com.chatapp.chatapp.model.Chatmessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

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

    private final Set<String> activeUsernames = ConcurrentHashMap.newKeySet();

    @MessageMapping("/register")
    @SendTo("/topics/user-validation")
    public String registerUsername(String username) {
        if (activeUsernames.contains(username)) {
            return "ERROR: Username already taken";
        }
        activeUsernames.add(username);
        return "OK";
    }

}
