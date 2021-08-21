package com.kirk3110.platycodon.controller.props;

import com.kirk3110.platycodon.model.Message;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChatProps {
    Integer roomId;
    List<Message> savedMessages;
}
