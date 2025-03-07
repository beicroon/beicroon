package com.beicroon.service.admin.dao.repository;

import com.beicroon.construct.database.repository.GenericPageRepository;
import com.beicroon.construct.database.wrapper.IQueryWrapper;
import com.beicroon.provider.mybatisplus.repository.MybatisplusPageRepository;
import com.beicroon.service.admin.dao.mapper.AdminResourceMapper;
import com.beicroon.service.admin.dao.model.AdminResourceModel;
import com.beicroon.service.admin.dao.tab.AdminResourceTabHandler;
import com.beicroon.service.admin.entity.adminresource.dto.AdminResourceQueryDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminResourceRepository extends MybatisplusPageRepository<AdminResourceQueryDTO, AdminResourceMapper, AdminResourceModel> implements GenericPageRepository<AdminResourceQueryDTO, AdminResourceModel> {

    @Resource
    private List<AdminResourceTabHandler> tabHandlers;

    @Override
    public List<AdminResourceTabHandler> getTabHandlers() {
        return this.tabHandlers;
    }

    @Override
    public IQueryWrapper<AdminResourceModel> fillQueryWrapper(AdminResourceQueryDTO dto, IQueryWrapper<AdminResourceModel> wrapper) {
        return wrapper;
    }

}
