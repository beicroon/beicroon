package com.beicroon.starter.generic.entity;

import com.beicroon.construct.constant.RegexConstant;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@ToString
@RequiredArgsConstructor
public class Field implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String name;

    private final String type;

    private final String comment;

    private String snakeCaseName;

    public String name() {
        return this.name;
    }

    public String type() {
        return this.type;
    }

    public String comment() {
        return this.comment;
    }

    public String getSnakeCaseName() {
        if (this.snakeCaseName == null) {
            Pattern pattern = Pattern.compile(RegexConstant.CASE_SNAKE);

            Matcher matcher = pattern.matcher(this.name);

            StringBuilder camelCase = new StringBuilder();

            while (matcher.find()) {
                matcher.appendReplacement(camelCase, matcher.group(1).toUpperCase());
            }

            matcher.appendTail(camelCase);

            this.snakeCaseName = camelCase.toString();
        }

        return this.snakeCaseName;
    }

}
