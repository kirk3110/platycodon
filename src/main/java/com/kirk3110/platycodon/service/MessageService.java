package com.kirk3110.platycodon.service;

import com.kirk3110.platycodon.mapper.MessageMapper;
import com.kirk3110.platycodon.model.Message;
import com.kirk3110.platycodon.repository.DiceRollRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.HtmlUtils;

@Service
public class MessageService {

    private DiceRollRepository diceRollRepository;
    private MessageMapper messageMapper;

    public MessageService(DiceRollRepository diceRollRepository, MessageMapper messageMapper) {
        this.diceRollRepository = diceRollRepository;
        this.messageMapper = messageMapper;
    }

    public List<Message> analyzeMessage(Message message) {
        List<Message> messages = new ArrayList<>(Arrays.asList(
            new Message(HtmlUtils.htmlEscape(message.getName()),
                HtmlUtils.htmlEscape(message.getStatement()))
        ));

        this.diceRollRepository.tryDiceRoll(message.getStatement()).ifPresent(result -> {
            messages.add(
                new Message(HtmlUtils.htmlEscape("DiceBot"),
                    HtmlUtils.htmlEscape(result.getText()))
            );
        });

        return messages;
    }

    @Transactional
    public void saveMessages(List<Message> messages) {
        for (Message message : messages) {
            this.messageMapper.save(message);
        }
    }

}
