package com.kirk3110.platycodon.controller.form;

import java.util.Date;
import lombok.Data;

@Data
public class SendMessageForm {

    private String name;
    private String statement;
    private Date sentAt;
    private String color;
}
