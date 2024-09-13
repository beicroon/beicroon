package com.beicroon.starter.generic.content.repository;

import com.beicroon.starter.generic.entity.Table;
import com.beicroon.starter.generic.manager.PackageManager;

public class RelationRepositoryContent {

    private static final String CONTENT = """
            package {{package}}.repository;

            import {{package}}.mapper.{{filename}}Mapper;
            import {{package}}.model.{{filename}}Model;
            import com.beicroon.starter.mysql.repository.ISuperRepository;
            import com.beicroon.starter.mysql.repository.impl.SuperRepository;
            import org.springframework.stereotype.Component;

            @Component
            public class {{filename}}Repository extends SuperRepository<{{filename}}Mapper, {{filename}}Model> implements ISuperRepository<{{filename}}Model> {

            }
            """;

    public static String getContent(PackageManager packageManager, Table table) {
        return CONTENT
                .replace("{{package}}", packageManager.getDaoPackage())
                .replace("{{modulePackage}}", packageManager.getEntityPackage() + "." + table.getPackage())
                .replace("{{filename}}", table.getFilename());
    }
    
}
