package com.beicroon.starter.generic.content.dao;

import com.beicroon.starter.generic.database.Table;
import com.beicroon.starter.generic.structure.GenericStructure;

public final class TabHandlerImplContent {

    private static final String CONTENT = """
            package {{modulePackage}}.dao.tab.impl;
            
            import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
            import {{modulePackage}}.dao.model.{{className}}Model;
            import {{modulePackage}}.dao.tab.{{className}}TabHandler;
            import org.springframework.stereotype.Component;
            
            @Component
            public class {{className}}AllTabHandler implements {{className}}TabHandler {
            
                @Override
                public boolean count() {
                    return false;
                }
            
                @Override
                public String getCode() {
                    return "all";
                }
            
                @Override
                public String getName() {
                    return "全部";
                }
            
                @Override
                public void fillQuery(LambdaQueryWrapper<{{className}}Model> query) {
            
                }
            
            }
            """;

    private TabHandlerImplContent() {
    }

    public static String getContent(GenericStructure structure, Table table) {
        return CONTENT
                .replace("{{modulePackage}}", structure.getModulePackage())
                .replace("{{className}}", table.getClassName())
                ;
    }
    
}
