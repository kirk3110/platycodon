package com.kirk3110.platycodon.controller.form;

import lombok.Data;

@Data
public class SendCharacterForm {
    Integer characterId;
    String name;
    String params;
    Integer initiative;
}
