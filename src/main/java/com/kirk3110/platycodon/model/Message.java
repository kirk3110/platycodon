package com.kirk3110.platycodon.model;

import java.util.Date;
import lombok.Data;
import org.springframework.web.util.HtmlUtils;

@Data
public class Message {

    private String name;

    private String statement;

    private Date sentAt;

    private String color;

    public Message(String name, String statement, Date sentAt, String color) {
        this.name = HtmlUtils.htmlEscape(name);
        this.statement = HtmlUtils.htmlEscape(statement);
        this.sentAt = sentAt;
        this.color = color;
    }

    public String getUnescapedName() {
        return HtmlUtils.htmlUnescape(this.name);
    }

    public String getUnescapedStatement() {
        return HtmlUtils.htmlUnescape(this.statement);
    }
}
