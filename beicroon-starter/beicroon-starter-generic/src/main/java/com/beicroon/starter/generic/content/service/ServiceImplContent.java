package com.beicroon.starter.generic.content.service;

import com.beicroon.starter.generic.entity.Table;
import com.beicroon.starter.generic.manager.PackageManager;

public class ServiceImplContent {

    private static final String CONTENT = """
            package {{package}}.service.impl;

            import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
            import com.beicroon.construct.entity.IdsDTO;
            import com.beicroon.construct.entity.PageInfo;
            import com.beicroon.construct.utils.List;
            import {{daoPackage}}.convertor.{{filename}}Convertor;
            import {{daoPackage}}.model.{{filename}}Model;
            import {{daoPackage}}.repository.{{filename}}Repository;
            import {{modulePackage}}.dto.{{filename}}CreateDTO;
            import {{modulePackage}}.dto.{{filename}}QueryDTO;
            import {{modulePackage}}.dto.{{filename}}UpdateDTO;
            import {{modulePackage}}.vo.{{filename}}BaseVO;
            import {{modulePackage}}.vo.{{filename}}DetailVO;
            import {{modulePackage}}.vo.{{filename}}PageVO;
            import {{package}}.service.I{{filename}}Service;
            import com.beicroon.starter.mysql.utils.PageUtils;
            import jakarta.annotation.Resource;
            import org.springframework.stereotype.Service;

            import java.util.List;

            @Service
            public class {{filename}}Service implements I{{filename}}Service {

                @Resource
                private {{filename}}Convertor {{variableName}}Convertor;

                @Resource
                private {{filename}}Repository {{variableName}}Repository;

                @Override
                public {{filename}}BaseVO show(Long id) {
                    {{filename}}Model {{variableName}} = this.{{variableName}}Repository.getById(id);

                    return this.{{variableName}}Convertor.toBaseVO({{variableName}});
                }

                @Override
                public {{filename}}DetailVO detail(Long id) {
                    {{filename}}Model {{variableName}} = this.{{variableName}}Repository.getById(id);
                   \s
                    return this.{{variableName}}Convertor.toDetailVO({{variableName}});
                }

                @Override
                public List<{{filename}}BaseVO> list({{filename}}QueryDTO dto) {
                    List<{{filename}}Model> list = this.{{variableName}}Repository.list(dto);
                   \s
                    return List.toList(list, this.{{variableName}}Convertor::toBaseVO);
                }

                @Override
                public PageInfo<{{filename}}PageVO> page({{filename}}QueryDTO dto) {
                    Page<{{filename}}Model> page = this.{{variableName}}Repository.page(dto);
                   \s
                    return PageUtils.result(page, this.{{variableName}}Convertor::toPageVO);
                }

                @Override
                public boolean create({{filename}}CreateDTO dto) {
                    {{filename}}Model creator = this.{{variableName}}Convertor.toEntity(dto);
                   \s
                    return this.{{variableName}}Repository.save(creator);
                }

                @Override
                public boolean update({{filename}}UpdateDTO dto) {
                    {{filename}}Model updater = this.{{variableName}}Convertor.toEntity(dto);
                   \s
                    return this.{{variableName}}Repository.updateById(updater);
                }

                @Override
                public boolean remove(IdsDTO dto) {
                    return this.{{variableName}}Repository.removeByIds(dto.getIds());
                }

            }
            """;

    public static String getContent(PackageManager packageManager, Table table) {
        return CONTENT
                .replace("{{package}}", packageManager.getWebPackage())
                .replace("{{daoPackage}}", packageManager.getDaoPackage())
                .replace("{{modulePackage}}", packageManager.getEntityPackage() + "." + table.getPackage())
                .replace("{{variableName}}", table.getVariableName())
                .replace("{{filename}}", table.getFilename());
    }

}
