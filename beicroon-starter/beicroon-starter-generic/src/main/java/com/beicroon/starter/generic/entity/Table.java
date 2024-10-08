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

    private static final String SUFFIX_RELATION = "_relation";

    private final String prefix;

    private final String name;

    private final String suffix;

    private final boolean isRelation;

    private final String comment;

    private final List<Field> columns;

    private final String filename;

    private final String vueFilename;

    private final String variableName;

    @Setter
    private Set<String> imports;

    @Setter
    private String modelContent;

    @Setter
    private String dtoContent;

    @Setter
    private String queryContent;

    @Setter
    private String vueHttpContent;

    @Setter
    private String vueSearchContent;

    @Setter
    private String vueMoreSearchContent;

    @Setter
    private String vueTableHeadContent;

    @Setter
    private String vueTableBodyContent;

    @Setter
    private String vueFormInputString;

    @Setter
    private String vueFormFieldString;

    @Setter
    private String vueFormDisabledInputString;

    public Table(String prefix, String name, String comment, List<Field> columns) {
        this.prefix = prefix.replace("_", "-");

        String tempName = name.replaceFirst(prefix, "");

        if (tempName.endsWith(SUFFIX_RELATION)) {
            this.suffix = SUFFIX_RELATION;

            this.isRelation = true;

            tempName = tempName.substring(0, tempName.length() - SUFFIX_RELATION.length());
        } else {
            this.suffix = "";

            this.isRelation = false;
        }

        if (tempName.startsWith("_")) {
            this.name = tempName.substring(1);
        } else {
            this.name = tempName;
        }

        this.comment = comment;

        this.columns = columns;

        String[] names = this.name.split("_");

        this.filename = Arrays.stream(names)
                .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1))
                .collect(Collectors.joining());

        this.vueFilename = String.join("-", names);

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
        return this.prefix + "_" + this.name + this.suffix;
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
