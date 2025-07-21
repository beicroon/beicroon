package com.beicroon.starter.generic.database;

import com.beicroon.construct.utils.EmptyUtils;
import com.beicroon.construct.utils.FileUtils;
import com.beicroon.construct.utils.StringUtils;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

@Getter
public class Table implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private static final String SUFFIX_RELATION = "_relation";

    private final String tableName;

    private final String tableComment;

    private final List<Field> fields;

    private final boolean isRelation;

    private final String name;

    private final String className;

    private final String urlName;

    private final String pathName;

    private final String variableName;

    private final Set<String> imports = new HashSet<>();

    private final StringBuilder modelContent = new StringBuilder();

    private final StringBuilder queryContent = new StringBuilder();

    private final StringBuilder entityContent = new StringBuilder();

    private final StringBuilder mappingContent = new StringBuilder();

    private final StringBuilder vueFieldContent = new StringBuilder();

    private final StringBuilder vueSearchContent = new StringBuilder();

    private final StringBuilder vueTableContent = new StringBuilder();

    private final StringBuilder vueCreateContent = new StringBuilder();

    private final StringBuilder vueUpdateContent = new StringBuilder();

    private final StringBuilder vueDetailContent = new StringBuilder();

    public Table(String tableName, String tableComment, List<Field> fields) {
        this.tableName = tableName;

        this.tableComment = tableComment;

        this.fields = fields;

        this.isRelation = tableName.endsWith(SUFFIX_RELATION);

        if (this.isRelation) {
            this.name = tableName.substring(0, tableName.length() - SUFFIX_RELATION.length());
        } else {
            this.name = tableName;
        }

        this.className = StringUtils.splitAndToFirstUpper(this.name, "_");

        this.urlName = this.name.replace("_", "-").toLowerCase();

        this.pathName = this.name.replace("_", EmptyUtils.emptyString()).toLowerCase();

        this.variableName = StringUtils.toFirstLower(this.className);
    }

    public String getImportString() {
        if (this.imports.isEmpty()) {
            return "";
        }

        List<String> importList = new ArrayList<>(this.imports);

        Collections.sort(importList);

        return "\n" + String.join("\n", importList) + "\n";
    }

    public void addImport(String importString) {
        this.imports.add(importString);
    }

    public void addModelContent(String content) {
        this.modelContent.append(content);
    }

    public void addQueryContent(String content) {
        this.queryContent.append(content);
    }

    public void addEntityContent(String content) {
        this.entityContent.append(content);
    }

    public void addMappingContent(String content) {
        this.mappingContent.append(content);
    }

    public void addVueFieldContent(String content) {
        this.vueFieldContent.append(content);
    }

    public void addVueSearchContent(String content) {
        this.vueSearchContent.append(content);
    }

    public void addVueTableContent(String content) {
        this.vueTableContent.append(content);
    }

    public void addVueCreateContent(String content) {
        this.vueCreateContent.append(content);
    }

    public void addVueUpdateContent(String content) {
        this.vueUpdateContent.append(content);
    }

    public void addVueDetailContent(String content) {
        this.vueDetailContent.append(content);
    }

    public String getModelContent() {
        return this.modelContent.toString();
    }

    public String getQueryContent() {
        return this.queryContent.toString();
    }

    public String getEntityContent() {
        return this.entityContent.toString();
    }

    public String getVueFieldContent() {
        return this.vueFieldContent.toString();
    }

    public String getVueSearchContent() {
        return this.vueSearchContent.toString();
    }

    public String getVueTableContent() {
        return this.vueTableContent.toString();
    }

    public String getVueCreateContent() {
        return this.vueCreateContent.toString();
    }

    public String getVueUpdateContent() {
        return this.vueUpdateContent.toString();
    }

    public String getVueDetailContent() {
        return this.vueDetailContent.toString();
    }

    public String getControllerFile() {
        return String.format("Admin%sController.java", this.getClassName());
    }

    public String getServiceFile() {
        return String.format("I%sService.java", this.getClassName());
    }

    public String getServiceImplFile() {
        return String.format("%sService.java", this.getClassName());
    }

    public String getConvertorFile() {
        return String.format("%sConvertor.java", this.getClassName());
    }

    public String getHelperFile() {
        return String.format("%sHelper.java", this.getClassName());
    }

    public String getManagerFile() {
        return String.format("%sManager.java", this.getClassName());
    }

    public String getMapperFile() {
        return String.format("%sMapper.java", this.getClassName());
    }

    public String getModelFile() {
        return String.format("%sModel.java", this.getClassName());
    }

    public String getRepositoryFile() {
        return String.format("%sRepository.java", this.getClassName());
    }

    public String getTabFile() {
        return String.format("%sTabHandler.java", this.getClassName());
    }

    public String getTabAllFile() {
        return String.format("%sAllTabHandler.java", this.getClassName());
    }

    public String getQueryDTOFile() {
        return FileUtils.getPath(this.getPathName(), "dto", String.format("%sQueryDTO.java", this.getClassName()));
    }

    public String getCreateDTOFile() {
        return FileUtils.getPath(this.getPathName(), "dto", String.format("%sCreateDTO.java", this.getClassName()));
    }

    public String getUpdateDTOFile() {
        return FileUtils.getPath(this.getPathName(), "dto", String.format("%sUpdateDTO.java", this.getClassName()));
    }

    public String getBaseVOFile() {
        return FileUtils.getPath(this.getPathName(), "vo", String.format("%sBaseVO.java", this.getClassName()));
    }

    public String getPageVOFile() {
        return FileUtils.getPath(this.getPathName(), "vo", String.format("%sPageVO.java", this.getClassName()));
    }

    public String getDetailVOFile() {
        return FileUtils.getPath(this.getPathName(), "vo", String.format("%sDetailVO.java", this.getClassName()));
    }

    public String getVueModuleFile() {
        return "module.ts";
    }

    public String getVueAppFile() {
        return String.format("%sApp.vue", this.getClassName());
    }

    public String getVueCreateFile() {
        return String.format("%sCreate.vue", this.getClassName());
    }

    public String getVueUpdateFile() {
        return String.format("%sUpdate.vue", this.getClassName());
    }

    public String getVueDetailFile() {
        return String.format("%sDetail.vue", this.getClassName());
    }

    public String getVueDetailViewFile() {
        return "DetailView.vue";
    }

}
