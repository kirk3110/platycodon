package com.kirk3110.platycodon.model;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class Room {

    Integer roomId;

    String name;

    String diceBot;

    Boolean isArchived;

    Date lastEnteredAt;

    String characterParams;
}
