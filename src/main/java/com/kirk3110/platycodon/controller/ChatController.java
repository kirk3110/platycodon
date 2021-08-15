package com.kirk3110.platycodon.controller;

import com.kirk3110.platycodon.model.Message;
import com.kirk3110.platycodon.service.MessageServiceImpl;
import java.util.List;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ChatController {

    private MessageServiceImpl messageService;

    public ChatController(MessageServiceImpl messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/chat/{roomId}")
    public String get(@PathVariable Integer roomId, Model model) {
        model.addAttribute("savedMessages", messageService.fetchMessages(roomId));
        return "chat";
    }

    @MessageMapping("/message/{roomId}")
    @SendTo("/receive/messages/{roomId}")
    public List<Message> receive(@DestinationVariable Integer roomId, Message message)
        throws Exception {
        Thread.sleep(1000);
        List<Message> messages = this.messageService.analyzeMessage(message);
        this.messageService.saveMessages(messages, roomId);
        return messages;
    }
}