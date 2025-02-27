package com.beicroon.starter.generic.content.app;

import com.beicroon.starter.generic.database.Table;
import com.beicroon.starter.generic.structure.GenericStructure;

public final class ServiceImplContent {
    
    private static final String CONTENT = """
            package {{modulePackage}}.app.service.impl;

            import com.beicroon.construct.base.entity.IdsDTO;
            import com.beicroon.construct.base.entity.PageInfo;
            import com.beicroon.construct.base.entity.TabVO;
            import {{modulePackage}}.app.service.I{{className}}Service;
            import {{modulePackage}}.dao.convertor.{{className}}Convertor;
            import {{modulePackage}}.dao.manager.{{className}}Manager;
            import {{modulePackage}}.dao.model.{{className}}Model;
            import {{modulePackage}}.dao.repository.{{className}}Repository;
            import {{modulePackage}}.entity.{{pathName}}.dto.{{className}}CreateDTO;
            import {{modulePackage}}.entity.{{pathName}}.dto.{{className}}QueryDTO;
            import {{modulePackage}}.entity.{{pathName}}.dto.{{className}}UpdateDTO;
            import {{modulePackage}}.entity.{{pathName}}.vo.{{className}}BaseVO;
            import {{modulePackage}}.entity.{{pathName}}.vo.{{className}}DetailVO;
            import {{modulePackage}}.entity.{{pathName}}.vo.{{className}}PageVO;
            import jakarta.annotation.Resource;
            import org.springframework.stereotype.Service;
            
            import java.util.List;

            @Service
            public class {{className}}Service implements I{{className}}Service {

                @Resource
                private {{className}}Manager {{variableName}}Manager;

                @Resource
                private {{className}}Convertor {{variableName}}Convertor;

                @Resource
                private {{className}}Repository {{variableName}}Repository;
            
                @Override
                public List<TabVO> tab({{className}}QueryDTO dto) {
                    return this.{{variableName}}Repository.tab(dto);
                }

                @Override
                public List<{{className}}BaseVO> list({{className}}QueryDTO dto) {
                    List<{{className}}Model> list = this.{{variableName}}Repository.list(dto);

                    return this.{{variableName}}Convertor.toBaseVO(list);
                }

                @Override
                public PageInfo<{{className}}PageVO> page({{className}}QueryDTO dto) {
                    PageInfo<{{className}}Model> page = this.{{variableName}}Repository.page(dto);

                    return this.{{variableName}}Convertor.toPageVO(page);
                }

                @Override
                public Boolean create({{className}}CreateDTO dto) {
                    {{className}}Model model = this.{{variableName}}Convertor.toCreator(dto);

                    return this.{{variableName}}Repository.insert(model);
                }

                @Override
                public Boolean update({{className}}UpdateDTO dto) {
                    {{className}}Model model = this.{{variableName}}Convertor.toUpdater(dto);

                    return this.{{variableName}}Repository.updateById(model);
                }

                @Override
                public {{className}}BaseVO show(Long id) {
                    {{className}}Model model = this.{{variableName}}Repository.getById(id);

                    return this.{{variableName}}Convertor.toBaseVO(model);
                }

                @Override
                public {{className}}DetailVO detail(Long id) {
                    {{className}}Model model = this.{{variableName}}Repository.getById(id);

                    return this.{{variableName}}Convertor.toDetailVO(model);
                }

                @Override
                public Boolean remove(IdsDTO dto) {
                    return this.{{variableName}}Repository.removeByIds(dto.getIds());
                }

            }
            """;
    
    private ServiceImplContent() {
    }

    public static String getContent(GenericStructure structure, Table table) {
        return CONTENT
                .replace("{{modulePackage}}", structure.getModulePackage())
                .replace("{{pathName}}", table.getPathName())
                .replace("{{className}}", table.getClassName())
                .replace("{{variableName}}", table.getVariableName())
                ;
    }
    
}
