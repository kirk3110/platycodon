package com.kirk3110.platycodon.model;

import java.util.Date;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.util.HtmlUtils;

@Data
public class Message {

    @Size(max = 100)
    private String name;

    private String statement;

    @NotEmpty
    private Date sentAt;

    @Size(max = 7)
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
