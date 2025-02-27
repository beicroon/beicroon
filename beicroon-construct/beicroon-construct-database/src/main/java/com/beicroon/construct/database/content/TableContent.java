package com.beicroon.construct.database.content;

import com.beicroon.construct.annotation.TableField;
import com.beicroon.construct.annotation.TableId;
import com.beicroon.construct.annotation.TableName;
import com.beicroon.construct.database.helper.LambdaHelper;
import com.beicroon.construct.database.lambda.SFunction;
import com.beicroon.construct.database.model.GenericModel;
import com.beicroon.construct.utils.ClassUtils;
import com.beicroon.construct.utils.EmptyUtils;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public class TableContent {

    private final static Map<Class<?>, TableContent> CACHE = new ConcurrentHashMap<>();

    private final String tableName;

    private final String tableId;

    private final List<String> tableFields = new ArrayList<>();

    private final Map<String, String> tableFieldMap = new HashMap<>();

    public TableContent(Class<?> clazz) {
        TableName tableNameAnnotation = clazz.getAnnotation(TableName.class);

        this.tableName = tableNameAnnotation.value();

        List<String> tableIds = new ArrayList<>();

        ClassUtils.forAllField(clazz, field -> {
            TableId tableIdAnnotation = field.getAnnotation(TableId.class);

            if (tableIdAnnotation != null) {
                tableIds.add(tableIdAnnotation.value());

                this.tableFieldMap.put(field.getName(), tableIdAnnotation.value());

                return;
            }

            TableField tableFieldAnnotation = field.getAnnotation(TableField.class);

            if (tableFieldAnnotation != null) {
                this.tableFields.add(tableFieldAnnotation.name());

                this.tableFieldMap.put(field.getName(), tableFieldAnnotation.name());
            }
        });

        if (EmptyUtils.isEmpty(tableIds)) {
            throw new RuntimeException(String.format("[%s]没有@TableId主键注解", clazz));
        }

        if (tableIds.size() > 1) {
            throw new RuntimeException(String.format("[%s]拥有多个@TableId主键注解", clazz));
        }

        this.tableId = tableIds.get(0);

        tableFields.add(0, this.tableId);
    }

    public static TableContent getTableContent(Class<?> clazz) {
        if (CACHE.containsKey(clazz)) {
            return CACHE.get(clazz);
        }

        TableContent tableContent = new TableContent(clazz);

        CACHE.put(clazz, tableContent);

        return tableContent;
    }

    public static <T extends GenericModel> String getTableField(Class<T> clazz, SFunction<T, ?> fieldLambda) {
        TableContent tableContent = TableContent.getTableContent(clazz);

        String fieldName = LambdaHelper.getTableField(fieldLambda);

        return tableContent.getTableFieldMap().get(fieldName);
    }

}
