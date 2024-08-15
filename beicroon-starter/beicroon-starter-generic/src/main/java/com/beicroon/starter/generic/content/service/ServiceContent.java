package com.beicroon.starter.generic.content.service;

import com.beicroon.starter.generic.entity.Table;
import com.beicroon.starter.generic.manager.PackageManager;

public class ServiceContent {

    private static final String CONTENT = """
            package {{package}}.service;

            import com.beicroon.construct.entity.IdsDTO;
            import com.beicroon.construct.entity.PageInfo;
            import {{modulePackage}}.dto.{{filename}}CreateDTO;
            import {{modulePackage}}.dto.{{filename}}QueryDTO;
            import {{modulePackage}}.dto.{{filename}}UpdateDTO;
            import {{modulePackage}}.vo.{{filename}}BaseVO;
            import {{modulePackage}}.vo.{{filename}}DetailVO;
            import {{modulePackage}}.vo.{{filename}}PageVO;

            import java.util.List;

            public interface I{{filename}}Service {

                {{filename}}BaseVO show(Long id);

                {{filename}}DetailVO detail(Long id);

                List<{{filename}}BaseVO> list({{filename}}QueryDTO dto);

                PageInfo<{{filename}}PageVO> page({{filename}}QueryDTO dto);

                boolean create({{filename}}CreateDTO dto);

                boolean update({{filename}}UpdateDTO dto);

                boolean remove(IdsDTO dto);

            }
            """;

    public static String getContent(PackageManager packageManager, Table table) {
        return CONTENT
                .replaceAll("\\{\\{package}}", packageManager.getWebPackage())
                .replaceAll("\\{\\{modulePackage}}", packageManager.getEntityPackage() + "." + table.getPackage())
                .replaceAll("\\{\\{filename}}", table.getFilename());
    }

}
