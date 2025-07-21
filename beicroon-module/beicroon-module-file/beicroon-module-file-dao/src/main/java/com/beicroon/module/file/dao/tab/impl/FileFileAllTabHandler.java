package com.beicroon.module.file.dao.tab.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.beicroon.module.file.dao.model.FileFileModel;
import com.beicroon.module.file.dao.tab.FileFileTabHandler;
import org.springframework.stereotype.Component;

@Component
public class FileFileAllTabHandler implements FileFileTabHandler {

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
    public void fillQuery(LambdaQueryWrapper<FileFileModel> query) {

    }

}
