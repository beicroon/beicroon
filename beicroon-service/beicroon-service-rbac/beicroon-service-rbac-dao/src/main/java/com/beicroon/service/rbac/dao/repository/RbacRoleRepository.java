package com.beicroon.service.rbac.dao.repository;

import com.beicroon.construct.database.repository.GenericPageRepository;
import com.beicroon.construct.database.wrapper.IQueryWrapper;
import com.beicroon.provider.mybatisplus.repository.MybatisplusPageRepository;
import com.beicroon.service.rbac.dao.mapper.RbacRoleMapper;
import com.beicroon.service.rbac.dao.model.RbacRoleModel;
import com.beicroon.service.rbac.dao.tab.RbacRoleTabHandler;
import com.beicroon.service.rbac.entity.rbacrole.dto.RbacRoleQueryDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RbacRoleRepository extends MybatisplusPageRepository<RbacRoleQueryDTO, RbacRoleMapper, RbacRoleModel> implements GenericPageRepository<RbacRoleQueryDTO, RbacRoleModel> {

    @Resource
    private List<RbacRoleTabHandler> tabHandlers;

    @Override
    public List<RbacRoleTabHandler> getTabHandlers() {
        return this.tabHandlers;
    }

    @Override
    public IQueryWrapper<RbacRoleModel> fillQueryWrapper(RbacRoleQueryDTO dto, IQueryWrapper<RbacRoleModel> wrapper) {
        return wrapper;
    }

}
