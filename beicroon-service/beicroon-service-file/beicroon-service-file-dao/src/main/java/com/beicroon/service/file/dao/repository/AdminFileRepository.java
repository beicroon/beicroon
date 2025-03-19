package com.beicroon.service.file.dao.repository;

import com.beicroon.construct.database.repository.GenericRepository;
import com.beicroon.provider.mybatisplus.repository.MybatisplusRepository;
import com.beicroon.service.file.dao.mapper.AdminFileMapper;
import com.beicroon.service.file.dao.model.AdminFileModel;
import org.springframework.stereotype.Repository;

@Repository
public class AdminFileRepository extends MybatisplusRepository<AdminFileMapper, AdminFileModel> implements GenericRepository<AdminFileModel> {

}
