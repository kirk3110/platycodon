package com.kirk3110.platycodon.controller;

import com.kirk3110.platycodon.controller.helper.ChatHelper;
import com.kirk3110.platycodon.controller.props.ChatProps;
import com.kirk3110.platycodon.model.Message;
import com.kirk3110.platycodon.service.MessageService;
import com.kirk3110.platycodon.service.RoomService;
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

    private ChatHelper chatHelper;
    private MessageService messageService;

    public ChatController(MessageService messageService, RoomService roomService,
        ChatHelper chatHelper) {
        this.messageService = messageService;
        this.chatHelper = chatHelper;
    }

    @GetMapping("/chat/{roomId}")
    public String get(@PathVariable Integer roomId, Model model) {
        ChatProps props = chatHelper.makeChatProps(roomId, messageService.fetchMessages(roomId));
        model.addAttribute("props", props);
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
