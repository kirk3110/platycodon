package com.kirk3110.platycodon.repository;

import com.kirk3110.platycodon.model.DiceRollResult;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestOperations;

@Repository
public class DiceRollRepository {

    @Value("${platycodon.dicebot.url}")
    private String dicebotUrl;

    private RestOperations restOperations;

    public DiceRollRepository(RestTemplateBuilder restTemplateBuilder) {
        this.restOperations = restTemplateBuilder.build();
    }

    public Optional<DiceRollResult> tryDiceRoll(String command) {
        DiceRollResult diceRollResult;
        try {
            diceRollResult = this.restOperations
                .getForObject(dicebotUrl + "?command=" + command, DiceRollResult.class);
        } catch (HttpClientErrorException exception) {
            if (exception.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
                return Optional.empty();
            } else {
                throw exception;
            }
        }
        return Optional.of(diceRollResult);
    }
}
