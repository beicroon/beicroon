package com.beicroon.service.admin.dao.repository;

import com.beicroon.construct.database.repository.GenericPageRepository;
import com.beicroon.construct.database.wrapper.IQueryWrapper;
import com.beicroon.provider.mybatisplus.repository.MybatisplusPageRepository;
import com.beicroon.service.admin.dao.mapper.AdminRoleMapper;
import com.beicroon.service.admin.dao.model.AdminRoleModel;
import com.beicroon.service.admin.dao.tab.AdminRoleTabHandler;
import com.beicroon.service.admin.entity.adminrole.dto.AdminRoleQueryDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminRoleRepository extends MybatisplusPageRepository<AdminRoleQueryDTO, AdminRoleMapper, AdminRoleModel> implements GenericPageRepository<AdminRoleQueryDTO, AdminRoleModel> {

    @Resource
    private List<AdminRoleTabHandler> tabHandlers;

    @Override
    public List<AdminRoleTabHandler> getTabHandlers() {
        return this.tabHandlers;
    }

    @Override
    public IQueryWrapper<AdminRoleModel> fillQueryWrapper(AdminRoleQueryDTO dto, IQueryWrapper<AdminRoleModel> wrapper) {
        return wrapper;
    }

}
