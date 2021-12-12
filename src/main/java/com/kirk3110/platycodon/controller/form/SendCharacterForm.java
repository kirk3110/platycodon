package com.kirk3110.platycodon.controller.form;

import java.util.HashMap;
import lombok.Data;

@Data
public class SendCharacterForm {
    Integer characterId;
    String name;
    HashMap<String, String> params;
    Integer initiative;
}
