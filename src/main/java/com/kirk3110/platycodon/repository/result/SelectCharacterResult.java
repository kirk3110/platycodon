package com.kirk3110.platycodon.repository.result;

import lombok.Data;

@Data
public class SelectCharacterResult {
    Integer characterId;
    String name;
    String params;
    Integer initiative;
}
