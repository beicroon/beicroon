package com.beicroon.starter.generic.maker;

import com.beicroon.construct.constant.SystemConstant;
import com.beicroon.construct.utils.FileUtils;
import com.beicroon.starter.generic.content.app.ControllerContent;
import com.beicroon.starter.generic.content.app.ConvertorContent;
import com.beicroon.starter.generic.content.app.ServiceContent;
import com.beicroon.starter.generic.content.app.ServiceImplContent;
import com.beicroon.starter.generic.content.dao.*;
import com.beicroon.starter.generic.content.entity.*;
import com.beicroon.starter.generic.content.vue.*;
import com.beicroon.starter.generic.database.Field;
import com.beicroon.starter.generic.database.Table;
import com.beicroon.starter.generic.enums.DBDriverEnums;
import com.beicroon.starter.generic.structure.GenericStructure;

import java.io.File;
import java.lang.reflect.Array;
import java.sql.*;
import java.util.*;

public class TableModuleMaker {

    private static final Set<String> IGNORES_TABLES = new HashSet<>();

    private static final Map<String, String> VUE_TYPES = new HashMap<>();

    private static final Map<String, String> JAVA_TYPES = new HashMap<>();

    private static final Map<String, String> TYPE_IMPORTS = new HashMap<>();

    static {
        // 忽略的表
        IGNORES_TABLES.add("flyway_schema");
        IGNORES_TABLES.add("flyway_schema_history");
//        IGNORES_TABLES.add("banner_banner");
//        IGNORES_TABLES.add("base_config");
//        IGNORES_TABLES.add("file_file");
//        IGNORES_TABLES.add("rbac_account");
//        IGNORES_TABLES.add("rbac_account_role_relation");
//        IGNORES_TABLES.add("rbac_resource");
//        IGNORES_TABLES.add("rbac_role");
//        IGNORES_TABLES.add("rbac_role_resource_relation");

        // Vue类型
        VUE_TYPES.put("tinyint", "number");
        VUE_TYPES.put("smallint", "number");
        VUE_TYPES.put("mediumint", "number");
        VUE_TYPES.put("int", "number");

        // 数值类型
        JAVA_TYPES.put("tinyint", "Integer");
        JAVA_TYPES.put("smallint", "Integer");
        JAVA_TYPES.put("mediumint", "Integer");
        JAVA_TYPES.put("int", "Integer");
        JAVA_TYPES.put("bigint", "Long");
        JAVA_TYPES.put("float", "BigDecimal");
        JAVA_TYPES.put("double", "BigDecimal");
        JAVA_TYPES.put("decimal", "BigDecimal");

        // 字符串类型
        JAVA_TYPES.put("char", "String");
        JAVA_TYPES.put("varchar", "String");
        JAVA_TYPES.put("text", "String");
        JAVA_TYPES.put("longtext", "String");

        // 日期类型
        JAVA_TYPES.put("date", "LocalDate");
        JAVA_TYPES.put("time", "LocalTime");
        JAVA_TYPES.put("datetime", "LocalDateTime");
        JAVA_TYPES.put("timestamp", "LocalDateTime");

        // 需要添加import
        TYPE_IMPORTS.put("BigDecimal", "import java.math.BigDecimal;");
        TYPE_IMPORTS.put("LocalDate", "import java.time.LocalDate;");
        TYPE_IMPORTS.put("LocalTime", "import java.time.LocalTime;");
        TYPE_IMPORTS.put("LocalDateTime", "import java.time.LocalDateTime;");
    }

    private final DBDriverEnums driver;

    private final String host;

    private final String database;

    private final String username;

    private final String password;

    public TableModuleMaker(DBDriverEnums driver, String host, String database, String username, String password) {
        this.driver = driver;

        this.host = host;

        this.database = database;

        this.username = username;

        this.password = password;
    }

    public void genericVue(File rootPath, String... tableNames) {
        List<Table> tables = this.getTables(tableNames);

        for (Table table : tables) {
            if (IGNORES_TABLES.contains(table.getTableName()) || table.isRelation()) {
                continue;
            }

            for (Field field : table.getFields()) {
                this.addModuleField(table, field);
                this.addSearchField(table, field);
                this.addTableField(table, field);
                this.addCreateField(table, field);
                this.addUpdateField(table, field);
                this.addDetailField(table, field);
            }

            File modulePath = new File(rootPath, FileUtils.getPath("src", "modules", table.getPathName()));
            File componentPath = new File(modulePath, "component");
            File scriptPath = new File(modulePath, "script");

            FileUtils.mkdir(modulePath);
            FileUtils.mkdir(componentPath);
            FileUtils.mkdir(scriptPath);

            // module.ts
            FileUtils.writeIfNotExists(
                    scriptPath, table.getVueModuleFile(), VueModuleContent.getContent(table)
            );

            // App.vue
            FileUtils.writeIfNotExists(
                    modulePath, table.getVueAppFile(), VueAppContent.getContent(table)
            );

            // Create.vue
            FileUtils.writeIfNotExists(
                    modulePath, table.getVueCreateFile(), VueCreateContent.getContent(table)
            );

            // Update.vue
            FileUtils.writeIfNotExists(
                    modulePath, table.getVueUpdateFile(), VueUpdateContent.getContent(table)
            );

            // Detail.vue
            FileUtils.writeIfNotExists(
                    modulePath, table.getVueDetailFile(), VueDetailContent.getContent(table)
            );

            // DetailView.vue
            FileUtils.writeIfNotExists(
                    componentPath, table.getVueDetailViewFile(), VueDetailViewContent.getContent(table)
            );
        }
    }

    private void addDetailField(Table table, Field field) {
        table.addVueDetailContent(String.format(
                "\n        <b-input disabled label=\"%s\" v-model=\"data.%s\"/>",
                field.getComment(),
                field.getSnakeCaseName()
        ));
    }

    private void addUpdateField(Table table, Field field) {
        table.addVueUpdateContent(String.format(
                "\n    module.params.%s = data.%s;  // %s",
                field.getSnakeCaseName(),
                field.getSnakeCaseName(),
                field.getComment()
        ));
    }

    private void addCreateField(Table table, Field field) {
        table.addVueCreateContent(String.format(
                "\n      <b-input label=\"%s\" v-model=\"module.params.%s\"/>",
                field.getComment(),
                field.getSnakeCaseName()
        ));
    }

    private void addTableField(Table table, Field field) {
        table.addVueTableContent(String.format(
                "\n    <b-table-column label=\"%s\" field=\"%s\"/>",
                field.getComment(),
                field.getSnakeCaseName()
        ));
    }

    private void addSearchField(Table table, Field field) {
        table.addVueSearchContent(String.format(
                "\n      <b-input label=\"%s\" v-model=\"module.params.%s\"/>",
                field.getComment(),
                field.getSnakeCaseName()
        ));
    }

    private void addModuleField(Table table, Field field) {
        String type = this.getVueType(field.getType());

        table.addVueFieldContent(String.format(
                "\n    %s?: %s,  // %s",
                field.getSnakeCaseName(),
                type,
                field.getComment()
        ));
    }

    private String getVueType(String type) {
        if (VUE_TYPES.containsKey(type)) {
            return VUE_TYPES.get(type);
        }

        return "string";
    }

    public void generic(GenericStructure structure, String... tableNames) {
        List<Table> tables = this.getTables(tableNames);

        for (Table table : tables) {
            if (IGNORES_TABLES.contains(table.getTableName())) {
                continue;
            }

            for (Field field : table.getFields()) {
                this.addModelField(table, field);
                this.addQueryField(table, field);
                this.addEntityField(table, field);
                this.addMappingField(table, field);
            }

            FileUtils.mkdir(new File(structure.getEntityJavaPath(), FileUtils.getPath(table.getPathName(), "dto")));
            FileUtils.mkdir(new File(structure.getEntityJavaPath(), FileUtils.getPath(table.getPathName(), "vo")));

            // mapper
            FileUtils.writeIfNotExists(
                    structure.getDaoMapperPath(), table.getMapperFile(), MapperContent.getContent(structure, table)
            );

            // model
            FileUtils.writeIfNotExists(
                    structure.getDaoModelPath(), table.getModelFile(), ModelContent.getContent(structure, table)
            );

            // baseVO
            FileUtils.writeIfNotExists(
                    structure.getEntityJavaPath(), table.getBaseVOFile(), VOBaseContent.getContent(structure, table)
            );

            // 接口
            if (!table.isRelation()) {
                // controller
                FileUtils.writeIfNotExists(
                        structure.getAppControllerAdminPath(), table.getControllerFile(), ControllerContent.getContent(structure, table)
                );

                // service
                FileUtils.writeIfNotExists(
                        structure.getAppServicePath(), table.getServiceFile(), ServiceContent.getContent(structure, table)
                );

                // service.impl
                FileUtils.writeIfNotExists(
                        structure.getAppServiceImplPath(), table.getServiceImplFile(), ServiceImplContent.getContent(structure, table)
                );

                // convertor
                FileUtils.writeIfNotExists(
                        structure.getDaoConvertorPath(), table.getConvertorFile(), ConvertorContent.getContent(structure, table)
                );

                // helper
                FileUtils.writeIfNotExists(
                        structure.getDaoHelperPath(), table.getHelperFile(), HelperContent.getContent(structure, table)
                );

                // manager
                FileUtils.writeIfNotExists(
                        structure.getDaoManagerPath(), table.getManagerFile(), ManagerContent.getContent(structure, table)
                );

                // repository
                FileUtils.writeIfNotExists(
                        structure.getDaoRepositoryPath(), table.getRepositoryFile(), RepositoryContent.getContent(structure, table)
                );

                // tabHandler
                FileUtils.writeIfNotExists(
                        structure.getDaoTabPath(), table.getTabFile(), TabHandlerContent.getContent(structure, table)
                );

                // tabHandlerAll
                FileUtils.writeIfNotExists(
                        structure.getDaoTabImplPath(), table.getTabAllFile(), TabHandlerImplContent.getContent(structure, table)
                );

                // queryDTO
                FileUtils.writeIfNotExists(
                        structure.getEntityJavaPath(), table.getQueryDTOFile(), DTOQueryContent.getContent(structure, table)
                );

                // createDTO
                FileUtils.writeIfNotExists(
                        structure.getEntityJavaPath(), table.getCreateDTOFile(), DTOCreateContent.getContent(structure, table)
                );

                // updateDTO
                FileUtils.writeIfNotExists(
                        structure.getEntityJavaPath(), table.getUpdateDTOFile(), DTOUpdateContent.getContent(structure, table)
                );

                // pageVO
                FileUtils.writeIfNotExists(
                        structure.getEntityJavaPath(), table.getPageVOFile(), VOPageContent.getContent(structure, table)
                );

                // detailVO
                FileUtils.writeIfNotExists(
                        structure.getEntityJavaPath(), table.getDetailVOFile(), VODetailContent.getContent(structure, table)
                );
            }
            // 非接口
            else {
                // repositoryRelation
                FileUtils.writeIfNotExists(
                        structure.getDaoRepositoryPath(), table.getRepositoryFile(), RepositoryRelationContent.getContent(structure, table)
                );
            }
        }
    }

    private void addEntityField(Table table, Field field) {
        String type = this.getJavaType(field.getType());

        if (TYPE_IMPORTS.containsKey(type)) {
            table.addImport(TYPE_IMPORTS.get(type));
        }

        table.addEntityContent(String.format(
                "\n    @Schema(name = \"%s\", description = \"%s\")\n    private %s %s;\n",
                field.getSnakeCaseName(),
                field.getComment(),
                type,
                field.getSnakeCaseName()
        ));
    }

    private void addMappingField(Table table, Field field) {
        table.addMappingContent(String.format(
                "\n    @Mapping(target = \"%s\", source = \"dto.%s\")",
                field.getSnakeCaseName(),
                field.getSnakeCaseName()
        ));
    }

    private void addQueryField(Table table, Field field) {
        String type = this.getJavaType(field.getType());

        if (TYPE_IMPORTS.containsKey(type)) {
            table.addImport(TYPE_IMPORTS.get(type));
        }

        table.addQueryContent(String.format(
                "\n    @FieldSearch(value = \"`%s`\")\n    @Schema(name = \"%s\", description = \"%s\")\n    private %s %s;\n",
                field.getName(),
                field.getSnakeCaseName(),
                field.getComment(),
                type,
                field.getSnakeCaseName()
        ));
    }

    private void addModelField(Table table, Field field) {
        String type = this.getJavaType(field.getType());

        if (TYPE_IMPORTS.containsKey(type)) {
            table.addImport(TYPE_IMPORTS.get(type));
        }

        table.addModelContent(String.format(
                "\n    @TableField(value = \"`%s`\")\n    @Schema(name = \"%s\", description = \"%s\")\n    private %s %s;\n",
                field.getName(),
                field.getSnakeCaseName(),
                field.getComment(),
                type,
                field.getSnakeCaseName()
        ));
    }

    private String getJavaType(String type) {
        if (!JAVA_TYPES.containsKey(type)) {
            return "String";
        }

        return JAVA_TYPES.get(type);
    }

    private List<Table> getTables(String... tableNames) {
        List<Table> tables = new ArrayList<>();

        Connection connection = this.getConnection();

        Statement statement = this.getStatement(connection);

        try {
            Map<String, String> tableNameMap = this.getDatabaseTable(statement, tableNames);

            for (Map.Entry<String, String> tableEntry : tableNameMap.entrySet()) {
                String tableName = tableEntry.getKey();

                String tableComment = tableEntry.getValue();

                List<Field> fields = this.getDatabaseFiled(statement, tableName);

                tables.add(new Table(tableName, tableComment, fields));
            }
        } finally {
            this.closeStatement(statement);

            this.closeConnection(connection);
        }

        return tables;
    }

    private List<Field> getDatabaseFiled(Statement statement, String tableName) {
        List<Field> columns = new ArrayList<>();

        ResultSet resultSet = null;

        try {
            String sql = this.buildFieldSql(tableName);

            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String name = resultSet.getString(1);

                if (SystemConstant.IGNORE_COLUMNS.contains(name)) {
                    continue;
                }

                String type = resultSet.getString(2);

                String comment = resultSet.getString(3);

                columns.add(new Field(name, type, comment));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            this.closeResultSet(resultSet);
        }

        return columns;
    }

    private String buildFieldSql(String tableName) {
        return String.format(
                "SELECT `COLUMN_NAME`, `DATA_TYPE`, `COLUMN_COMMENT` " +
                "FROM `INFORMATION_SCHEMA`.`COLUMNS` " +
                "WHERE `TABLE_NAME` = '%s' " +
                "AND `TABLE_SCHEMA` = '%s' " +
                "ORDER BY `ORDINAL_POSITION`",
                tableName,
                this.database
        );
    }

    private Map<String, String> getDatabaseTable(Statement statement, String... tableNames) {
        Map<String, String> tables = new HashMap<>();

        ResultSet resultSet = null;

        try {
            String sql = this.buildTableSql(tableNames);

            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                tables.put(resultSet.getString(1), resultSet.getString(2));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            this.closeResultSet(resultSet);
        }

        return tables;
    }

    private String buildTableSql(String... tableNames) {
        StringBuilder sb = new StringBuilder(String.format(
                "SELECT `TABLE_NAME`, `TABLE_COMMENT` " +
                "FROM `INFORMATION_SCHEMA`.`TABLES` " +
                "WHERE `TABLE_SCHEMA` = '%s'",
                this.database
        ));

        if (Array.getLength(tableNames) > 0) {
            sb.append(" AND `TABLE_NAME` IN ('").append(String.join("', '", tableNames)).append("')");
        }

        return sb.toString();
    }

    private void closeResultSet(ResultSet resultSet) {
        if (resultSet == null) {
            return;
        }

        try {
            resultSet.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private Statement getStatement(Connection connection) {
        try {
            return connection.createStatement();
        } catch (SQLException ex) {
            this.closeConnection(connection);

            throw new RuntimeException(ex);
        }
    }

    private void closeStatement(Statement statement) {
        if (statement == null) {
            return;
        }

        try {
            statement.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private Connection getConnection() {
        try {
            Class.forName(this.driver.getDriver());

            String url = String.format("jdbc:%s://%s/%s", this.driver.getType(), this.host, this.database);

            return DriverManager.getConnection(url, this.username, this.password);
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void closeConnection(Connection connection) {
        if (connection == null) {
            return;
        }

        try {
            connection.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
