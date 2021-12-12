package com.kirk3110.platycodon.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kirk3110.platycodon.mapper.CharacterMapper;
import com.kirk3110.platycodon.model.Character;
import com.kirk3110.platycodon.repository.result.SelectCharacterResult;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class CharacterRepository {

    private CharacterMapper characterMapper;
    private ObjectMapper objectMapper;

    public CharacterRepository(CharacterMapper characterMapper, ObjectMapper objectMapper) {
        this.characterMapper = characterMapper;
        this.objectMapper = objectMapper;
    }

    public void insertCharacter(Character character, Integer roomId) {
        String paramString = "{}";
        try {
            paramString = objectMapper.writeValueAsString(character.getParams());
        } catch (JsonProcessingException e) {
            // paramsは空のまま
        }
        characterMapper.insertCharacter(character, paramString, roomId);
    }

    public List<Character> selectByRoomId(Integer roomId, Integer limit) {
        List<SelectCharacterResult> results = characterMapper.selectByRoomId(roomId, limit);

        List<Character> characters = new ArrayList<>();
        for(SelectCharacterResult result : results) {
            HashMap<String, String> params = new HashMap<>();
            try {
                params = objectMapper.readValue(result.getParams(), HashMap.class);
            } catch(JsonProcessingException e) {
                // paramsは空のまま
            }
            characters.add(
                new Character(
                    result.getCharacterId(),
                    result.getName(),
                    params,
                    result.getInitiative())
            );
        }
        return characters;
    }
}
