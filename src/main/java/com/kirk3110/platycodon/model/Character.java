package com.kirk3110.platycodon.model;

import java.util.HashMap;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Character {

    Integer characterId;
    String name;
    HashMap<String, String> params;
    Integer initiative;
}
