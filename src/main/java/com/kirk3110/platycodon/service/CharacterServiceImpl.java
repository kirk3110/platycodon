package com.kirk3110.platycodon.service;

import com.kirk3110.platycodon.model.Character;
import com.kirk3110.platycodon.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
public class CharacterServiceImpl implements CharacterService {

    private CharacterRepository characterRepository;

    public CharacterServiceImpl(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @Override
    public void putCharacter(Character character, Integer roomId) {
        characterRepository.insertCharacter(character, roomId);
    }
}
