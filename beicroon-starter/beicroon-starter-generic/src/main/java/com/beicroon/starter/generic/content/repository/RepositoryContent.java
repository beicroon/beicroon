package com.beicroon.starter.generic.content.repository;

import com.beicroon.starter.generic.entity.Table;
import com.beicroon.starter.generic.manager.PackageManager;

public class RepositoryContent {

    private static final String CONTENT = """
            package {{package}}.repository;

            import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
            import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
            import {{package}}.mapper.{{filename}}Mapper;
            import {{package}}.model.{{filename}}Model;
            import {{modulePackage}}.dto.{{filename}}QueryDTO;
            import com.beicroon.starter.mysql.repository.IGenericRepository;
            import com.beicroon.starter.mysql.repository.impl.GenericRepository;
            import org.springframework.stereotype.Component;

            @Component
            public class {{filename}}Repository extends GenericRepository<{{filename}}QueryDTO, {{filename}}Mapper, {{filename}}Model> implements IGenericRepository<{{filename}}QueryDTO, {{filename}}Model> {

                @Override
                public void fillQueryWrapper({{filename}}QueryDTO dto, QueryWrapper<{{filename}}Model> wrapper) {
                    LambdaQueryWrapper<{{filename}}Model> query = wrapper.lambda();
                }

            }
            """;

    public static String getContent(PackageManager packageManager, Table table) {
        return CONTENT
                .replaceAll("\\{\\{package}}", packageManager.getDaoPackage())
                .replaceAll("\\{\\{modulePackage}}", packageManager.getEntityPackage() + "." + table.getPackage())
                .replaceAll("\\{\\{filename}}", table.getFilename());
    }
    
}
