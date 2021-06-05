package com.kirk3110.platycodon.controller;

import com.kirk3110.platycodon.model.Message;
import com.kirk3110.platycodon.service.MessageService;
import java.util.List;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

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
        List<Message> messages = this.messageService.analyzeMessage(message);
        this.messageService.saveMessages(messages);
        return messages;
    }
}
