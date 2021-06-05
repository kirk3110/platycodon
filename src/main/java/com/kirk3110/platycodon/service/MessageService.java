package com.kirk3110.platycodon.service;

import com.kirk3110.platycodon.mapper.MessageMapper;
import com.kirk3110.platycodon.model.Message;
import com.kirk3110.platycodon.repository.DiceRollRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MessageService {

    @Value("${platycodon.message.maxLogLength}")
    private Integer MAX_LOG_LENGTH;

    private DiceRollRepository diceRollRepository;
    private MessageMapper messageMapper;

    public MessageService(DiceRollRepository diceRollRepository, MessageMapper messageMapper) {
        this.diceRollRepository = diceRollRepository;
        this.messageMapper = messageMapper;
    }

    public List<Message> analyzeMessage(Message message) {
        List<Message> messages = new ArrayList<>(Arrays.asList(message));

        this.diceRollRepository.tryDiceRoll(message.getStatement()).ifPresent(result -> {
            messages.add(new Message("DiceBot", result.getText(), new Date()));
        });

        return messages;
    }

    public List<Message> getMessageLog(Integer roomId) {
        return this.messageMapper.findBy(roomId, MAX_LOG_LENGTH);
    }

    @Transactional
    public void saveMessages(List<Message> messages, Integer roomId) {
        for (Message message : messages) {
            this.messageMapper.save(message, roomId);
        }
    }

}
