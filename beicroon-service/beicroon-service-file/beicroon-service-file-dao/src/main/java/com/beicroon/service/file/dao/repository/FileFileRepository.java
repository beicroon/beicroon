package com.beicroon.service.file.dao.repository;

import com.beicroon.construct.database.repository.GenericRepository;
import com.beicroon.provider.mybatisplus.repository.MybatisplusRepository;
import com.beicroon.service.file.dao.mapper.FileFileMapper;
import com.beicroon.service.file.dao.model.FileFileModel;
import org.springframework.stereotype.Repository;

@Repository
public class FileFileRepository extends MybatisplusRepository<FileFileMapper, FileFileModel> implements GenericRepository<FileFileModel> {

}
