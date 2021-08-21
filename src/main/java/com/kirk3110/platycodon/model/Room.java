package com.kirk3110.platycodon.model;

import java.util.Date;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Room {

    @NotNull
    Integer roomId;

    @NotEmpty
    String name;
    
    String diceBot;

    Boolean isArchived;

    Date lastEnteredAt;
}
