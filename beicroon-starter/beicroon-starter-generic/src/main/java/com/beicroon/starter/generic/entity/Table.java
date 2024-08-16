package com.beicroon.starter.generic.entity;

import com.beicroon.starter.generic.utils.FileUtils;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Getter
public class Table implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String prefix;

    private final String name;

    private final String comment;

    private final List<Field> columns;

    private final String filename;

    private final String variableName;

    @Setter
    private Set<String> imports;

    @Setter
    private String modelContent;

    @Setter
    private String dtoContent;

    @Setter
    private String queryContent;

    public Table(String prefix, String name, String comment, List<Field> columns) {
        this.prefix = prefix;

        this.name = name.replaceFirst(prefix, "");

        this.comment = comment;

        this.columns = columns;

        this.filename = Arrays.stream(this.name.split("_"))
                .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1))
                .collect(Collectors.joining());

        this.variableName = this.filename.substring(0, 1).toLowerCase() + this.filename.substring(1);
    }

    public String getImportString() {
        if (this.imports == null || this.imports.isEmpty()) {
            return "";
        }

        List<String> importList = new ArrayList<>(this.imports);

        Collections.sort(importList);

        return "\n" + String.join("\n", importList) + "\n";
    }

    public String getTableName() {
        return this.prefix + this.name;
    }

    public String getPath() {
        return this.name.replace("_", FileUtils.getSeparator());
    }

    public String getPackage() {
        return this.name.replace("_", ".");
    }

    public String getUrlName() {
        return this.name.replace("_", "-");
    }

}
