package com.kirk3110.platycodon.controller.helper;

import com.kirk3110.platycodon.controller.form.SendMessageForm;
import com.kirk3110.platycodon.controller.props.RoomProps;
import com.kirk3110.platycodon.model.Message;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.web.util.HtmlUtils;

@Component
public class RoomHelper {

    public RoomProps makeRoomProps(Integer roomId, List<Message> savedMessages,
        String characterParamsStr) {
        return RoomProps.builder()
            .roomId(roomId)
            .characterParams(Arrays.asList(characterParamsStr.split("\\s")))
            .savedMessages(savedMessages)
            .build();
    }

    public Message toMessage(SendMessageForm sendMessageForm) {
        return new Message(
            HtmlUtils.htmlEscape(sendMessageForm.getName()),
            HtmlUtils.htmlEscape(sendMessageForm.getStatement()),
            sendMessageForm.getSentAt(),
            sendMessageForm.getColor()
        );
    }
}
