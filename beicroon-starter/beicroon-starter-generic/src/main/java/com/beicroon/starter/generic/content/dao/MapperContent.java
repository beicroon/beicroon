package com.beicroon.starter.generic.content.dao;

import com.beicroon.starter.generic.database.Table;
import com.beicroon.starter.generic.structure.GenericStructure;

public final class MapperContent {

    private static final String CONTENT = """
            package {{modulePackage}}.dao.mapper;
            
            import com.beicroon.provider.mybatisplus.mapper.GenericMapper;
            import {{modulePackage}}.dao.model.{{className}}Model;
            import org.apache.ibatis.annotations.Mapper;
            
            @Mapper
            public interface {{className}}Mapper extends GenericMapper<{{className}}Model> {
            
            }
            """;

    private MapperContent() {
    }

    public static String getContent(GenericStructure structure, Table table) {
        return CONTENT
                .replace("{{modulePackage}}", structure.getModulePackage())
                .replace("{{className}}", table.getClassName())
                ;
    }
    
}
