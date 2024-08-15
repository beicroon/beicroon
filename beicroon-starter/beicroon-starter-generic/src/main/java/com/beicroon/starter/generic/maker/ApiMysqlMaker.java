package com.beicroon.starter.generic.maker;

import com.beicroon.construct.constant.RegexConstant;
import com.beicroon.construct.constant.SystemConstant;
import com.beicroon.starter.generic.content.ModelContent;
import com.beicroon.starter.generic.manager.FileManager;
import com.beicroon.starter.generic.manager.PackageManager;
import com.beicroon.starter.generic.utils.FileUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.File;
import java.io.Serial;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.sql.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ApiMysqlMaker {

    private static final Set<String> IGNORES_TABLES = new HashSet<>();

    private static final Map<String, String> JAVA_TYPES = new HashMap<>();

    private static final Map<String, String> TYPE_IMPORTS = new HashMap<>();

    static {
        // 忽略的表
        IGNORES_TABLES.add("flyway_schema");
        IGNORES_TABLES.add("flyway_schema_history");

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

    public ApiMysqlMaker(String host, String database, String username, String password) {
        this.host = host;

        this.database = database;

        this.username = username;

        this.password = password;
    }

    public void generic(File rootPath, String serviceName, String basePackage, String... tableNames) {
        this.run(rootPath, serviceName, basePackage, tableNames);
    }

    private void run(File rootPath, String serviceName, String basePackage, String... tableNames) {
        System.out.println("接口初始化开始");

        if (!rootPath.exists()) {
            throw new RuntimeException(String.format("项目根目录不存在[%s]", rootPath.getAbsolutePath()));
        }

        PackageManager packageManager = new PackageManager(basePackage);

        FileManager fileManager = new FileManager(rootPath, serviceName, packageManager);

        for (Table table : this.getTables(tableNames)) {
            Set<String> imports = new HashSet<>();

            StringBuilder content = new StringBuilder();

            for (Field field : table.getColumns()) {
                content.append(this.getModelFieldString(field, imports));
            }

            table.setImports(imports);
            table.setContent(content.toString());

            File modelFile = FileUtils.getJavaFile(fileManager.getModelPath(), table.getFileName() + "Model");

            FileUtils.writeFileIfNotExists(modelFile, ModelContent.getContent(packageManager, table));
        }

        System.out.println("接口初始化结束");
    }

    private String getPropertyFieldString(Field field, Set<String> imports) {
        String type = this.getJavaType(field.getType());

        if (TYPE_IMPORTS.containsKey(type)) {
            imports.add(TYPE_IMPORTS.get(type));
        }

        return String.format(
                "\n    @ApiModelProperty(name = \"%s\")\n    private %s %s;\n",
                field.getComment(),
                type,
                this.snakeToCamel(field.getName())
        );
    }

    private String getSearchFieldString(Field field, Set<String> imports) {
        String type = this.getJavaType(field.getType());

        if (TYPE_IMPORTS.containsKey(type)) {
            imports.add(TYPE_IMPORTS.get(type));
        }

        return String.format(
                "\n    @FieldSearch(value = \"`%s`\")\n    @ApiModelProperty(name = \"%s\")\n    private %s %s;\n",
                field.getName(),
                field.getComment(),
                type,
                this.snakeToCamel(field.getName())
        );
    }

    private String getModelFieldString(Field field, Set<String> imports) {
        String type = this.getJavaType(field.getType());

        if (TYPE_IMPORTS.containsKey(type)) {
            imports.add(TYPE_IMPORTS.get(type));
        }

        return String.format(
                "\n    @TableField(value = \"`%s`\")\n    @ApiModelProperty(name = \"%s\")\n    private %s %s;\n",
                field.getName(),
                field.getComment(),
                type,
                this.snakeToCamel(field.getName())
        );
    }

    private String snakeToCamel(String snakeCase) {
        Pattern pattern = Pattern.compile(RegexConstant.CASE_SNAKE);

        Matcher matcher = pattern.matcher(snakeCase);

        StringBuilder camelCase = new StringBuilder();

        while (matcher.find()) {
            matcher.appendReplacement(camelCase, matcher.group(1).toUpperCase());
        }

        matcher.appendTail(camelCase);

        return camelCase.toString();
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
            Map<String, String> tableNameMap = this.queryTables(statement, tableNames);

            for (Map.Entry<String, String> tableEntry : tableNameMap.entrySet()) {
                String tableName = tableEntry.getKey();

                String tableComment = tableEntry.getValue();

                List<Field> fields = this.queryColumns(statement, tableName);

                tables.add(new Table(tableName, tableComment, fields));
            }
        } finally {
            this.closeStatement(statement);

            this.closeConnection(connection);
        }

        return tables;
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
                String tableName = resultSet.getString(1);

                if (IGNORES_TABLES.contains(tableName)) {
                    continue;
                }

                tables.put(tableName, resultSet.getString(2));
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

    @Getter
    @ToString
    public static class Table implements Serializable {

        @Serial
        private static final long serialVersionUID = 1L;

        private final String name;

        private final String comment;

        private final List<Field> columns;

        private final String fileName;

        @Setter
        private Set<String> imports;

        @Setter
        private String content;

        public Table(String name, String comment, List<Field> columns) {
            this.name = name;

            this.comment = comment;

            this.columns = columns;

            this.fileName = Arrays.stream(name.split("_"))
                    .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1))
                    .collect(Collectors.joining());
        }

        public String getImportString() {
            if (this.imports == null || this.imports.isEmpty()) {
                return "";
            }

            List<String> importList = new ArrayList<>(this.imports);

            Collections.sort(importList);

            return "\n" + String.join("\n", importList) + "\n";
        }

    }

    @Getter
    @ToString
    public static class Field implements Serializable {

        @Serial
        private static final long serialVersionUID = 1L;

        private final String name;

        private final String type;

        private final String comment;

        public Field(String name, String type, String comment) {
            this.name = name;

            this.type = type;

            this.comment = comment;
        }

    }

}
