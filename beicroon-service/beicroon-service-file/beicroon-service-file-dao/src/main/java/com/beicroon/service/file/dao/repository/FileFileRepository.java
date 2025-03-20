package com.beicroon.service.file.dao.repository;

import com.beicroon.construct.database.repository.GenericPageRepository;
import com.beicroon.construct.database.wrapper.IQueryWrapper;
import com.beicroon.provider.mybatisplus.repository.MybatisplusPageRepository;
import com.beicroon.service.file.dao.mapper.FileFileMapper;
import com.beicroon.service.file.dao.model.FileFileModel;
import com.beicroon.service.file.dao.tab.FileFileTabHandler;
import com.beicroon.service.file.entity.filefile.dto.FileFileQueryDTO;
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
    public IQueryWrapper<FileFileModel> fillQueryWrapper(FileFileQueryDTO dto, IQueryWrapper<FileFileModel> wrapper) {
        return wrapper;
    }

}
