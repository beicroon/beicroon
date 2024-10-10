package com.beicroon.starter.generic.content.mapper;

import com.beicroon.starter.generic.entity.Table;
import com.beicroon.starter.generic.manager.PackageManager;

public class MapperContent {

    private static final String CONTENT = """
            package {{package}}.mapper;
            
            import {{package}}.model.{{filename}}Model;
            import com.beicroon.starter.mysql.mapper.GenericMapper;
            import org.apache.ibatis.annotations.Mapper;
            
            @Mapper
            public interface {{filename}}Mapper extends GenericMapper<{{filename}}Model> {
            
            }
            """;

    public static String getContent(PackageManager packageManager, Table table) {
        return CONTENT
                .replace("{{package}}", packageManager.getDaoPackage())
                .replace("{{filename}}", table.getFilename());
    }

}
