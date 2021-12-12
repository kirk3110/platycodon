package com.kirk3110.platycodon.repository;

import com.kirk3110.platycodon.mapper.CharacterMapper;
import com.kirk3110.platycodon.model.Character;
import org.springframework.stereotype.Repository;

@Repository
public class CharacterRepository {

    private CharacterMapper characterMapper;

    public CharacterRepository(CharacterMapper characterMapper) {
        this.characterMapper = characterMapper;
    }

    public void insertCharacter(Character character, Integer roomId) {
        this.characterMapper.insertCharacter(character, character.getParams().toString(), roomId);
    }
}
