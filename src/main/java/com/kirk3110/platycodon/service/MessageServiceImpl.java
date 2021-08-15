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
public class MessageServiceImpl implements MessageService {

    @Value("${platycodon.message.maxFetchMessagesLength}")
    private Integer MAX_FETCH_MESSAGES_LENGTH;

    private DiceRollRepository diceRollRepository;
    private MessageMapper messageMapper;

    public MessageServiceImpl(DiceRollRepository diceRollRepository, MessageMapper messageMapper) {
        this.diceRollRepository = diceRollRepository;
        this.messageMapper = messageMapper;
    }

    @Override
    public List<Message> analyzeMessage(Message message) {
        List<Message> messages = new ArrayList<>(Arrays.asList(message));
        this.diceRollRepository.tryDiceRoll(message.getStatement()).ifPresent(result -> {
            messages.add(new Message("DiceBot", result.getText(), new Date()));
        });
        return messages;
    }

    @Override
    @Transactional
    public void saveMessages(List<Message> messages, Integer roomId) {
        for (Message message : messages) {
            this.messageMapper.save(message, roomId);
        }
    }

    @Override
    public List<Message> fetchMessages(Integer roomId) {
        return this.messageMapper.findBy(roomId, MAX_FETCH_MESSAGES_LENGTH);
    }

}
