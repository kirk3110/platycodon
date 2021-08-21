package com.kirk3110.platycodon.controller;

import com.kirk3110.platycodon.controller.helper.TopHelper;
import com.kirk3110.platycodon.controller.props.TopProps;
import com.kirk3110.platycodon.service.MessageServiceImpl;
import com.kirk3110.platycodon.service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TopController {

    private TopHelper topHelper;
    private RoomService roomService;

    public TopController(RoomService roomService, TopHelper topHelper) {
        this.roomService = roomService;
        this.topHelper = topHelper;
    }

    @GetMapping("/")
    public String get(Model model) {
        TopProps props = topHelper.makeTopProps(roomService.fetchRooms());
        model.addAttribute("props", props);
        return "index";
    }
}
