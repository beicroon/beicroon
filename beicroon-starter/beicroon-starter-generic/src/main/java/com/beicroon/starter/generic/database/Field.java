package com.beicroon.starter.generic.database;

import com.beicroon.construct.constant.RegexConstant;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
public class Field implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String name;

    private final String type;

    private final String comment;

    private final String snakeCaseName;

    public Field(String name, String type, String comment) {
        this.name = name;

        this.type = type;

        this.comment = comment;

        this.snakeCaseName = this.getSnakeCaseName();
    }

    public String getSnakeCaseName() {
        Pattern pattern = Pattern.compile(RegexConstant.CASE_SNAKE);

        Matcher matcher = pattern.matcher(this.name);

        StringBuilder camelCase = new StringBuilder();

        while (matcher.find()) {
            matcher.appendReplacement(camelCase, matcher.group(1).toUpperCase());
        }

        matcher.appendTail(camelCase);

        return camelCase.toString();
    }

}
