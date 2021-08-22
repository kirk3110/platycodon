package com.kirk3110.platycodon.model;

import java.util.Date;
import lombok.Data;

@Data
public class Room {

    Integer roomId;

    String name;

    String diceBot;

    Boolean isArchived;

    Date lastEnteredAt;
}
