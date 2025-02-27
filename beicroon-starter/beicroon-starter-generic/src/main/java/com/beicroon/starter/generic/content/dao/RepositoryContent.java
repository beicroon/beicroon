package com.beicroon.starter.generic.content.dao;

import com.beicroon.starter.generic.database.Table;
import com.beicroon.starter.generic.structure.GenericStructure;

public final class RepositoryContent {

    private static final String CONTENT = """
            package {{modulePackage}}.dao.repository;
            
            import com.beicroon.construct.database.repository.GenericPageRepository;
            import com.beicroon.construct.database.wrapper.IQueryWrapper;
            import com.beicroon.provider.mybatisplus.repository.MybatisplusPageRepository;
            import {{modulePackage}}.dao.mapper.{{className}}Mapper;
            import {{modulePackage}}.dao.model.{{className}}Model;
            import {{modulePackage}}.dao.tab.{{className}}TabHandler;
            import {{modulePackage}}.entity.{{pathName}}.dto.{{className}}QueryDTO;
            import jakarta.annotation.Resource;
            import org.springframework.stereotype.Repository;
            
            import java.util.List;
            
            @Repository
            public class {{className}}Repository extends MybatisplusPageRepository<{{className}}QueryDTO, {{className}}Mapper, {{className}}Model> implements GenericPageRepository<{{className}}QueryDTO, {{className}}Model> {
            
                @Resource
                private List<{{className}}TabHandler> tabHandlers;
        
                @Override
                public List<{{className}}TabHandler> getTabHandlers() {
                    return this.tabHandlers;
                }
            
                @Override
                public IQueryWrapper<{{className}}Model> fillQueryWrapper({{className}}QueryDTO dto, IQueryWrapper<{{className}}Model> wrapper) {
                    return wrapper;
                }
            
            }
            """;

    private RepositoryContent() {
    }

    public static String getContent(GenericStructure structure, Table table) {
        return CONTENT
                .replace("{{modulePackage}}", structure.getModulePackage())
                .replace("{{pathName}}", table.getPathName())
                .replace("{{className}}", table.getClassName())
                ;
    }
    
}
