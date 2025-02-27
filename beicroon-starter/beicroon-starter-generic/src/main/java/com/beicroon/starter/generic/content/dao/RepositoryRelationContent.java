package com.beicroon.starter.generic.content.dao;

import com.beicroon.starter.generic.database.Table;
import com.beicroon.starter.generic.structure.GenericStructure;

public final class RepositoryRelationContent {

    private static final String CONTENT = """
            package {{modulePackage}}.dao.repository;
            
            import com.beicroon.construct.database.repository.GenericRepository;
            import com.beicroon.provider.mybatisplus.repository.MybatisplusRepository;
            import {{modulePackage}}.dao.mapper.{{className}}Mapper;
            import {{modulePackage}}.dao.model.{{className}}Model;
            import org.springframework.stereotype.Repository;
            
            @Repository
            public class {{className}}Repository extends MybatisplusRepository<{{className}}Mapper, {{className}}Model> implements GenericRepository<{{className}}Model> {
            
            }
            """;

    private RepositoryRelationContent() {
    }

    public static String getContent(GenericStructure structure, Table table) {
        return CONTENT
                .replace("{{modulePackage}}", structure.getModulePackage())
                .replace("{{className}}", table.getClassName())
                ;
    }
    
}
