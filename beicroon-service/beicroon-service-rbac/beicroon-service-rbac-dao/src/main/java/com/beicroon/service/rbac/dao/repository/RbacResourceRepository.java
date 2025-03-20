package com.beicroon.service.rbac.dao.repository;

import com.beicroon.construct.database.repository.GenericPageRepository;
import com.beicroon.construct.database.wrapper.IQueryWrapper;
import com.beicroon.provider.mybatisplus.repository.MybatisplusPageRepository;
import com.beicroon.service.rbac.dao.mapper.RbacResourceMapper;
import com.beicroon.service.rbac.dao.model.RbacResourceModel;
import com.beicroon.service.rbac.dao.tab.RbacResourceTabHandler;
import com.beicroon.service.rbac.entity.rbacresource.dto.RbacResourceQueryDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RbacResourceRepository extends MybatisplusPageRepository<RbacResourceQueryDTO, RbacResourceMapper, RbacResourceModel> implements GenericPageRepository<RbacResourceQueryDTO, RbacResourceModel> {

    @Resource
    private List<RbacResourceTabHandler> tabHandlers;

    @Override
    public List<RbacResourceTabHandler> getTabHandlers() {
        return this.tabHandlers;
    }

    @Override
    public IQueryWrapper<RbacResourceModel> fillQueryWrapper(RbacResourceQueryDTO dto, IQueryWrapper<RbacResourceModel> wrapper) {
        return wrapper;
    }

}
