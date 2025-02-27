package com.beicroon.provider.mybatisplus.injector;

import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import org.apache.ibatis.builder.MapperBuilderAssistant;

public class MybatisPlusSqlInjector extends DefaultSqlInjector {

    @Override
    public void inspectInject(MapperBuilderAssistant builderAssistant, Class<?> mapperClass) {
        super.inspectInject(builderAssistant, mapperClass);
    }

}
