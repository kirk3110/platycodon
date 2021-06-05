package com.kirk3110.platycodon.controller;

import com.kirk3110.platycodon.model.Message;
import com.kirk3110.platycodon.service.MessageService;
import java.util.List;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MessageController {

    private MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @MessageMapping("/message")
    @SendTo("/receive/messages")
    public List<Message> send(Message message) throws Exception {
        Thread.sleep(1000);
        Message escapedMessage = new Message(message.getName(), message.getStatement(), message.getSentAt());
        List<Message> messages = this.messageService.analyzeMessage(escapedMessage);
        this.messageService.saveMessages(messages, 1);
        return messages;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/chat")
    public String chat(Model model) {
        model.addAttribute("savedMessages", messageService.getMessageLog(1));
        return "chat";
    }
}
