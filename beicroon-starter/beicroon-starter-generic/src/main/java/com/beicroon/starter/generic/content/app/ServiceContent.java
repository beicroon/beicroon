package com.beicroon.starter.generic.content.app;

import com.beicroon.starter.generic.database.Table;
import com.beicroon.starter.generic.structure.GenericStructure;

public final class ServiceContent {

    private static final String CONTENT = """
            package {{modulePackage}}.app.service;

            import com.beicroon.construct.base.entity.IdsDTO;
            import com.beicroon.construct.base.entity.PageInfo;
            import com.beicroon.construct.base.entity.TabVO;
            import {{modulePackage}}.entity.{{pathName}}.dto.{{className}}CreateDTO;
            import {{modulePackage}}.entity.{{pathName}}.dto.{{className}}QueryDTO;
            import {{modulePackage}}.entity.{{pathName}}.dto.{{className}}UpdateDTO;
            import {{modulePackage}}.entity.{{pathName}}.vo.{{className}}BaseVO;
            import {{modulePackage}}.entity.{{pathName}}.vo.{{className}}DetailVO;
            import {{modulePackage}}.entity.{{pathName}}.vo.{{className}}PageVO;

            import java.util.List;

            public interface I{{className}}Service {

                List<TabVO> tab({{className}}QueryDTO dto);

                List<{{className}}BaseVO> list({{className}}QueryDTO dto);

                PageInfo<{{className}}PageVO> page({{className}}QueryDTO dto);

                Boolean create({{className}}CreateDTO dto);

                Boolean update({{className}}UpdateDTO dto);

                {{className}}BaseVO show(Long id);

                {{className}}DetailVO detail(Long id);

                Boolean remove(IdsDTO dto);

            }
            """;

    private ServiceContent() {
    }

    public static String getContent(GenericStructure structure, Table table) {
        return CONTENT
                .replace("{{modulePackage}}", structure.getModulePackage())
                .replace("{{pathName}}", table.getPathName())
                .replace("{{className}}", table.getClassName())
                ;
    }
    
}
