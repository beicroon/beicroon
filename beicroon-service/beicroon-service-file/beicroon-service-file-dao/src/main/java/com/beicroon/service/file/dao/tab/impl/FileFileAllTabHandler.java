package com.beicroon.service.file.dao.tab.impl;

import com.beicroon.construct.database.wrapper.IQueryWrapper;
import com.beicroon.service.file.dao.model.FileFileModel;
import com.beicroon.service.file.dao.tab.FileFileTabHandler;
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
    public void fillQuery(IQueryWrapper<FileFileModel> query) {

    }

}
