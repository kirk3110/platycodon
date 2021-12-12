package com.kirk3110.platycodon.service;

import com.kirk3110.platycodon.model.Character;
import java.util.List;

public interface CharacterService {

    void putCharacter(Character character, Integer roomId);

    List<Character> fetchCharacters(Integer roomId);
}
