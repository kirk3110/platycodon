package com.kirk3110.platycodon.service;

import com.kirk3110.platycodon.model.Message;
import java.util.List;

public interface MessageService {

    List<Message> analyzeMessage(Message message);

    void saveMessages(List<Message> messages, Integer roomId);

    List<Message> fetchMessages(Integer roomId);
}
