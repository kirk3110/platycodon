package com.kirk3110.platycodon.controller.helper;

import com.kirk3110.platycodon.controller.props.RoomProps;
import com.kirk3110.platycodon.model.Message;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class RoomHelper {

    public RoomProps makeChatProps(Integer roomId, List<Message> savedMessages) {
        return RoomProps.builder()
            .roomId(roomId)
            .savedMessages(savedMessages)
            .build();
    }
}
