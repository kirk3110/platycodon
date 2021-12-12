package com.kirk3110.platycodon.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Character {

    Integer characterId;
    String name;
    String params;
    Integer initiative;
}
