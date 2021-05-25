package com.kirk3110.platycodon.model;

import lombok.Data;

@Data
public class DiceRollResult {

    Boolean ok;
    String text;
    Boolean secret;
    Boolean success;
    Boolean failure;
    Boolean critical;
    Boolean fumble;
}
