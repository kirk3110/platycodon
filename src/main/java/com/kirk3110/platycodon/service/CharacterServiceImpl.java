package com.kirk3110.platycodon.service;

import com.kirk3110.platycodon.model.Character;
import com.kirk3110.platycodon.repository.CharacterRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CharacterServiceImpl implements CharacterService {

    @Value("${platycodon.message.maxFetchCharactersLength}")
    private Integer MAX_FETCH_CHARACTERS_LENGTH;

    private CharacterRepository characterRepository;

    public CharacterServiceImpl(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @Override
    public void putCharacter(Character character, Integer roomId) {
        characterRepository.insertCharacter(character, roomId);
    }

    @Override
    public List<Character> fetchCharacters(Integer roomId) {
        return characterRepository.selectByRoomId(roomId, MAX_FETCH_CHARACTERS_LENGTH);
    }
}
