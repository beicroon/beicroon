package com.beicroon.module.base.dao.tab.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.beicroon.module.base.dao.model.BaseConfigModel;
import com.beicroon.module.base.dao.tab.BaseConfigTabHandler;
import org.springframework.stereotype.Component;

@Component
public class BaseConfigAllTabHandler implements BaseConfigTabHandler {

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
    public void fillQuery(LambdaQueryWrapper<BaseConfigModel> query) {

    }

}
