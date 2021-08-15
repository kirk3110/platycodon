package com.kirk3110.platycodon.controller;

import com.kirk3110.platycodon.model.Message;
import com.kirk3110.platycodon.service.MessageServiceImpl;
import java.util.List;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {

    private MessageServiceImpl messageService;

    public ChatController(MessageServiceImpl messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/chat")
    public String get(Model model) {
        model.addAttribute("savedMessages", messageService.fetchMessages(1));
        return "chat";
    }

    @MessageMapping("/message")
    @SendTo("/receive/messages")
    public List<Message> receive(Message message) throws Exception {
        Thread.sleep(1000);
        List<Message> messages = this.messageService.analyzeMessage(message);
        this.messageService.saveMessages(messages, 1);
        return messages;
    }
}
