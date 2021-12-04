package com.kirk3110.platycodon.controller;

import com.kirk3110.platycodon.controller.helper.RoomHelper;
import com.kirk3110.platycodon.controller.props.RoomProps;
import com.kirk3110.platycodon.model.Message;
import com.kirk3110.platycodon.model.Character;
import com.kirk3110.platycodon.service.CharacterService;
import com.kirk3110.platycodon.service.MessageService;
import com.kirk3110.platycodon.service.RoomService;
import java.util.List;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RoomController {

    private RoomHelper roomHelper;
    private MessageService messageService;
    private RoomService roomService;
    private CharacterService characterService;

    public RoomController(MessageService messageService, RoomService roomService,
        CharacterService characterService, RoomHelper roomHelper) {
        this.messageService = messageService;
        this.roomService = roomService;
        this.characterService = characterService;
        this.roomHelper = roomHelper;
    }

    @GetMapping("/room/{roomId}")
    public String get(@PathVariable Integer roomId, Model model) {
        RoomProps props = roomHelper.makeRoomProps(roomId, messageService.fetchMessages(roomId),
            roomService.selectById(roomId).getCharacterParams());
        roomService.updateLastEnteredAt(roomId);
        model.addAttribute("props", props);
        return "room";
    }

    @MessageMapping("/message/{roomId}")
    @SendTo("/receive/messages/{roomId}")
    public List<Message> receiveMessage(@DestinationVariable Integer roomId, Message message)
        throws Exception {
        Thread.sleep(1000);
        List<Message> messages = this.messageService.analyzeMessage(message);
        this.messageService.saveMessages(messages, roomId);
        return messages;
    }

    @MessageMapping("/character/{roomId}")
    @SendTo("/receive/character/{roomId}")
    public Character receiveCharacter(@DestinationVariable Integer roomId, Character character)
        throws Exception {
        Thread.sleep(1000);
        this.characterService.putCharacter(character, roomId);
        return character;
    }
}
