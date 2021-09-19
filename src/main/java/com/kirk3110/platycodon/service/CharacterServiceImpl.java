package com.kirk3110.platycodon.service;

import com.kirk3110.platycodon.mapper.CharacterMapper;
import com.kirk3110.platycodon.model.Character;
import org.springframework.stereotype.Service;

@Service
public class CharacterServiceImpl implements CharacterService {

    private CharacterMapper characterMapper;

    public CharacterServiceImpl(CharacterMapper characterMapper) {
        this.characterMapper = characterMapper;
    }

    @Override
    public void putCharacter(Character character, Integer roomId) {
        characterMapper.insertCharacter(character, roomId);
    }
}
