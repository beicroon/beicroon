package com.beicroon.starter.generic.maker;

import com.beicroon.construct.constant.SystemConstant;
import com.beicroon.starter.generic.content.controller.AdminControllerContent;
import com.beicroon.starter.generic.content.convertor.ConvertorContent;
import com.beicroon.starter.generic.content.dto.CreateDTOContent;
import com.beicroon.starter.generic.content.dto.QueryDTOContent;
import com.beicroon.starter.generic.content.dto.UpdateDTOContent;
import com.beicroon.starter.generic.content.helper.HelperContent;
import com.beicroon.starter.generic.content.manager.ManagerContent;
import com.beicroon.starter.generic.content.mapper.MapperContent;
import com.beicroon.starter.generic.content.mapper.MapperXmlContent;
import com.beicroon.starter.generic.content.model.ModelContent;
import com.beicroon.starter.generic.content.repository.RepositoryContent;
import com.beicroon.starter.generic.content.service.ServiceContent;
import com.beicroon.starter.generic.content.service.ServiceImplContent;
import com.beicroon.starter.generic.content.vo.BaseVOContent;
import com.beicroon.starter.generic.content.vo.DetailVOContent;
import com.beicroon.starter.generic.content.vo.PageVOContent;
import com.beicroon.starter.generic.content.vue.*;
import com.beicroon.starter.generic.entity.Field;
import com.beicroon.starter.generic.entity.Table;
import com.beicroon.starter.generic.manager.FileManager;
import com.beicroon.starter.generic.manager.PackageManager;
import com.beicroon.starter.generic.utils.FileUtils;

import java.io.File;
import java.lang.reflect.Array;
import java.sql.*;
import java.util.*;

public class ModuleMysqlMaker {

    private static final Set<String> IGNORES_TABLES = new HashSet<>();

    private static final Map<String, String> NUMBER_TYPES = new HashMap<>();

    private static final Map<String, String> JAVA_TYPES = new HashMap<>();

    private static final Map<String, String> TYPE_IMPORTS = new HashMap<>();

    static {
        // 忽略的表
        IGNORES_TABLES.add("flyway_schema");
        IGNORES_TABLES.add("flyway_schema_history");
        IGNORES_TABLES.add("admin_account_admin");
        IGNORES_TABLES.add("admin_resource_menu");

        NUMBER_TYPES.put("Integer", "number");

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

    private final String host;

    private final String database;

    private final String username;

    private final String password;

    private List<Table> tables;

    public ModuleMysqlMaker(String host, String database, String username, String password) {
        this.host = host;

        this.database = database;

        this.username = username;

        this.password = password;
    }

    public void generic(File rootPath, String moduleName, String basePackage, String modulePrefix, String... tableNames) {
        this.run(rootPath, moduleName, basePackage, modulePrefix, tableNames);
    }

    public void genericVueModule(File rootPath, String modulePrefix, String... tableNames) {
        if (this.tables == null) {
            this.initTables(modulePrefix, tableNames);
        }

        for (Table table : this.tables) {
            File modulePath = new File(rootPath, table.getPath());

            FileUtils.mkdir(modulePath);

            StringBuilder vueHttpContent = new StringBuilder();
            StringBuilder vueSearchContent = new StringBuilder();
            StringBuilder vueMoreSearchContent = new StringBuilder();
            StringBuilder vueTableHeadContent = new StringBuilder();
            StringBuilder vueTableBodyContent = new StringBuilder();
            StringBuilder vueFormInputString = new StringBuilder();
            StringBuilder vueFormFieldString = new StringBuilder();
            StringBuilder vueFormDisabledInputString = new StringBuilder();

            int searchCount = 1;

            for (Field field : table.getColumns()) {
                vueHttpContent.append(this.getVueHttpFieldString(field));

                if (searchCount <= 9) {
                    vueSearchContent.append(this.getVueSearchFieldString(field));

                    searchCount++;
                }

                vueMoreSearchContent.append(this.getVueMoreSearchFieldString(field));
                vueTableHeadContent.append(this.getVueTableHeadFieldString(field));
                vueTableBodyContent.append(this.getVueTableBodyFieldString(field));
                vueFormInputString.append(this.getVueFormInputFieldString(field));
                vueFormFieldString.append(this.getVueFormFieldFieldString(field));
                vueFormDisabledInputString.append(this.getVueFormDisabledInputFieldString(field));
            }

            table.setVueHttpContent(vueHttpContent.toString());
            table.setVueSearchContent(vueSearchContent.toString().trim());
            table.setVueMoreSearchContent(vueMoreSearchContent.toString().trim());
            table.setVueTableHeadContent(vueTableHeadContent.toString().trim());
            table.setVueTableBodyContent(vueTableBodyContent.toString().trim());
            table.setVueFormInputString(vueFormInputString.toString().trim());
            table.setVueFormFieldString(vueFormFieldString.toString().trim());
            table.setVueFormDisabledInputString(vueFormDisabledInputString.toString().trim());

            FileUtils.writeFileIfNotExists(FileManager.getVueHttpFile(modulePath, table), VueHttpContent.getContent(table));
            FileUtils.writeFileIfNotExists(FileManager.getVueAppFile(modulePath, table), VueAppContent.getContent(table));
            FileUtils.writeFileIfNotExists(FileManager.getVueCreateFile(modulePath, table), VueCreateContent.getContent(table));
            FileUtils.writeFileIfNotExists(FileManager.getVueDetailFile(modulePath, table), VueDetailContent.getContent(table));
            FileUtils.writeFileIfNotExists(FileManager.getVueUpdateFile(modulePath, table), VueUpdateContent.getContent(table));
        }
    }

    private void run(File rootPath, String moduleName, String basePackage, String modulePrefix, String... tableNames) {
        System.out.println("接口初始化开始");

        if (!rootPath.exists()) {
            throw new RuntimeException(String.format("项目根目录不存在[%s]", rootPath.getAbsolutePath()));
        }

        PackageManager packageManager = new PackageManager(basePackage);

        FileManager fileManager = new FileManager(rootPath, moduleName, packageManager);

        this.initTables(modulePrefix, tableNames);

        for (Table table : this.tables) {
            if (IGNORES_TABLES.contains(table.getTableName())) {
                continue;
            }

            Set<String> imports = new HashSet<>();

            StringBuilder modelContent = new StringBuilder();
            StringBuilder dtoContent = new StringBuilder();
            StringBuilder queryContent = new StringBuilder();

            for (Field field : table.getColumns()) {
                modelContent.append(this.getModelFieldString(field, imports));
                dtoContent.append(this.getDTOFieldString(field, imports));
                queryContent.append(this.getQueryFieldString(field, imports));
            }

            table.setImports(imports);
            table.setModelContent(modelContent.toString());
            table.setDtoContent(dtoContent.toString());
            table.setQueryContent(queryContent.toString());

            FileUtils.writeFileIfNotExists(fileManager.getConverTorFile(table), ConvertorContent.getContent(packageManager, table));
            FileUtils.writeFileIfNotExists(fileManager.getHelperFile(table), HelperContent.getContent(packageManager, table));
            FileUtils.writeFileIfNotExists(fileManager.getManagerFile(table), ManagerContent.getContent(packageManager, table));
            FileUtils.writeFileIfNotExists(fileManager.getMapperFile(table), MapperContent.getContent(packageManager, table));
            FileUtils.writeFileIfNotExists(fileManager.getModelFile(table), ModelContent.getContent(packageManager, table));
            FileUtils.writeFileIfNotExists(fileManager.getRepositoryFile(table), RepositoryContent.getContent(packageManager, table));
            FileUtils.writeFileIfNotExists(fileManager.getMapperXmlFile(table), MapperXmlContent.getContent(packageManager, table));
            FileUtils.writeFileIfNotExists(fileManager.getCreateDTOFile(table), CreateDTOContent.getContent(packageManager, table));
            FileUtils.writeFileIfNotExists(fileManager.getUpdateDTOFile(table), UpdateDTOContent.getContent(packageManager, table));
            FileUtils.writeFileIfNotExists(fileManager.getQueryDTOFile(table), QueryDTOContent.getContent(packageManager, table));
            FileUtils.writeFileIfNotExists(fileManager.getBaseVOFile(table), BaseVOContent.getContent(packageManager, table));
            FileUtils.writeFileIfNotExists(fileManager.getDetailVOFile(table), DetailVOContent.getContent(packageManager, table));
            FileUtils.writeFileIfNotExists(fileManager.getPageVOFile(table), PageVOContent.getContent(packageManager, table));
            FileUtils.writeFileIfNotExists(fileManager.getControllerAdminFile(table), AdminControllerContent.getContent(packageManager, table));
            FileUtils.writeFileIfNotExists(fileManager.getServiceFile(table), ServiceContent.getContent(packageManager, table));
            FileUtils.writeFileIfNotExists(fileManager.getServiceImplFile(table), ServiceImplContent.getContent(packageManager, table));
        }

        System.out.println("接口初始化结束");
    }

    private String getDTOFieldString(Field field, Set<String> imports) {
        String type = this.getJavaType(field.type());

        if (TYPE_IMPORTS.containsKey(type)) {
            imports.add(TYPE_IMPORTS.get(type));
        }

        return String.format(
                "\n    @ApiModelProperty(name = \"%s\")\n    private %s %s;\n",
                field.comment(),
                type,
                field.getSnakeCaseName()
        );
    }

    private String getQueryFieldString(Field field, Set<String> imports) {
        String type = this.getJavaType(field.type());

        if (TYPE_IMPORTS.containsKey(type)) {
            imports.add(TYPE_IMPORTS.get(type));
        }

        return String.format(
                "\n    @FieldSearch(name = \"`%s`\")\n    @ApiModelProperty(name = \"%s\")\n    private %s %s;\n",
                field.name(),
                field.comment(),
                type,
                field.getSnakeCaseName()
        );
    }

    private String getVueFormDisabledInputFieldString(Field field) {
        String template = """
                      <beicroon-input disabled class="column" label="%s" placeholder="%s" v-model="form.%s"></beicroon-input>
                """;

        return String.format(template, field.getComment(), field.getComment(), field.getSnakeCaseName());
    }

    private String getVueFormFieldFieldString(Field field) {
        String template = """
                  form.%s = data.%s;
                """;

        return String.format(template, field.getSnakeCaseName(), field.getSnakeCaseName());
    }

    private String getVueFormInputFieldString(Field field) {
        String template = """
                      <beicroon-input class="column" label="%s" placeholder="%s" v-model="form.%s"></beicroon-input>
                """;

        return String.format(template, field.getComment(), field.getComment(), field.getSnakeCaseName());
    }

    private String getVueTableBodyFieldString(Field field) {
        String template = """
                        <beicroon-list-cell>{{ item.%s }}</beicroon-list-cell>
                """;

        return String.format(template, field.getSnakeCaseName());
    }

    private String getVueTableHeadFieldString(Field field) {
        String template = """
                      <beicroon-list-cell width="180">%s</beicroon-list-cell>
                """;

        return String.format(template, field.comment());
    }

    private String getVueMoreSearchFieldString(Field field) {
        String template = """
                      <beicroon-input class="column" label="%s" v-model="list.params.%s"></beicroon-input>
                """;

        return String.format(template, field.comment(), field.getSnakeCaseName());
    }

    private String getVueSearchFieldString(Field field) {
        String template = """
                      <beicroon-input label="%s" v-model="list.params.%s"></beicroon-input>
                """;

        return String.format(template, field.comment(), field.getSnakeCaseName());
    }

    private String getVueHttpFieldString(Field field) {
        String type = this.getJavaType(field.type());

        if (NUMBER_TYPES.containsKey(type)) {
            return String.format("    %s?: number,  // %s\n", field.getSnakeCaseName(), field.getComment());
        }

        return String.format("    %s?: string,  // %s\n", field.getSnakeCaseName(), field.getComment());
    }

    private String getModelFieldString(Field field, Set<String> imports) {
        String type = this.getJavaType(field.type());

        if (TYPE_IMPORTS.containsKey(type)) {
            imports.add(TYPE_IMPORTS.get(type));
        }

        return String.format(
                "\n    @TableField(value = \"`%s`\")\n    @ApiModelProperty(name = \"%s\")\n    private %s %s;\n",
                field.name(),
                field.comment(),
                type,
                field.getSnakeCaseName()
        );
    }

    private String getJavaType(String type) {
        if (!JAVA_TYPES.containsKey(type)) {
            return "String";
        }

        return JAVA_TYPES.get(type);
    }

    private void initTables(String modulePrefix, String... tableNames) {
        this.tables = new ArrayList<>();

        Connection connection = this.getConnection();

        Statement statement = this.getStatement(connection);

        try {
            Map<String, String> tableNameMap = this.queryTables(statement, tableNames);

            for (Map.Entry<String, String> tableEntry : tableNameMap.entrySet()) {
                String tableName = tableEntry.getKey();

                String tableComment = tableEntry.getValue();

                List<Field> fields = this.queryColumns(statement, tableName);

                this.tables.add(new Table(modulePrefix, tableName, tableComment, fields));
            }
        } finally {
            this.closeStatement(statement);

            this.closeConnection(connection);
        }
    }

    private List<Field> queryColumns(Statement statement, String tableName) {
        List<Field> columns = new ArrayList<>();

        ResultSet resultSet = null;

        try {
            String sql = this.getColumnsSql(tableName);

            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String name = resultSet.getString(1);

                if (SystemConstant.IGNORES_COLUMNS.contains(name)) {
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

    private Map<String, String> queryTables(Statement statement, String... tableNames) {
        Map<String, String> tables = new HashMap<>();

        ResultSet resultSet = null;

        try {
            String sql = this.getTableSql(tableNames);

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

    private String getTableSql(String... tableNames) {
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

    private String getColumnsSql(String tableName) {
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

    private Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = String.format("jdbc:mysql://%s/%s", this.host, this.database);

            return DriverManager.getConnection(url, this.username, this.password);
        } catch (Exception ex) {
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

    private Statement getStatement(Connection connection) {
        try {
            return connection.createStatement();
        } catch (SQLException ex) {
            this.closeConnection(connection);

            throw new RuntimeException(ex);
        }
    }

}
