package com.beicroon.module.file.dao.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.beicroon.module.file.dao.mapper.FileFileMapper;
import com.beicroon.module.file.dao.model.FileFileModel;
import com.beicroon.module.file.dao.tab.FileFileTabHandler;
import com.beicroon.module.file.entity.filefile.dto.FileFileQueryDTO;
import com.beicroon.starter.dao.repository.GenericPageRepository;
import com.beicroon.starter.dao.repository.impl.MybatisplusPageRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FileFileRepository extends MybatisplusPageRepository<FileFileQueryDTO, FileFileMapper, FileFileModel> implements GenericPageRepository<FileFileQueryDTO, FileFileModel> {

    @Resource
    private List<FileFileTabHandler> tabHandlers;

    @Override
    public List<FileFileTabHandler> getTabHandlers() {
        return this.tabHandlers;
    }

    @Override
    public void fillQueryWrapper(FileFileQueryDTO dto, QueryWrapper<FileFileModel> wrapper) {

    }

}
