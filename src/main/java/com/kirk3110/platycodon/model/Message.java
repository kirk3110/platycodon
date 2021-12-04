package com.kirk3110.platycodon.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.util.HtmlUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    private String name;
    private String statement;
    private Date sentAt;
    private String color;

    public String getUnescapedName() {
        return HtmlUtils.htmlUnescape(this.name);
    }

    public String getUnescapedStatement() {
        return HtmlUtils.htmlUnescape(this.statement);
    }
}
