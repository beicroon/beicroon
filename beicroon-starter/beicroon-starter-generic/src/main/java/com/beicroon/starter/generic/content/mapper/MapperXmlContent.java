package com.beicroon.starter.generic.content.mapper;

import com.beicroon.starter.generic.entity.Table;
import com.beicroon.starter.generic.manager.PackageManager;

public class MapperXmlContent {

    private static final String CONTENT = """
            <?xml version="1.0" encoding="UTF-8"?>
            <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
            <mapper namespace="{{package}}.mapper.{{filename}}Mapper">
                        
            </mapper>
            """;

    public static String getContent(PackageManager packageManager, Table table) {
        return CONTENT
                .replace("{{package}}", packageManager.getDaoPackage())
                .replace("{{filename}}", table.getFilename());
    }

}
