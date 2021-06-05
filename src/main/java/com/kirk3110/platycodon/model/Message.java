package com.kirk3110.platycodon.model;

import java.util.Date;
import lombok.Data;
import org.springframework.web.util.HtmlUtils;

@Data
public class Message {

    private String name;
    private String statement;
    private Date sentAt;

    public Message(String name, String statement, Date sentAt) {
        this.name = HtmlUtils.htmlEscape(name);
        this.statement = HtmlUtils.htmlEscape(statement);
        this.sentAt = sentAt;
    }
}
