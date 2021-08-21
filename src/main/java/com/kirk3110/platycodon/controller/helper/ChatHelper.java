package com.kirk3110.platycodon.controller.helper;

import com.kirk3110.platycodon.controller.props.ChatProps;
import com.kirk3110.platycodon.model.Message;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ChatHelper {

    public ChatProps makeChatProps(Integer roomId, List<Message> savedMessages) {
        return ChatProps.builder()
            .roomId(roomId)
            .savedMessages(savedMessages)
            .build();
    }
}
