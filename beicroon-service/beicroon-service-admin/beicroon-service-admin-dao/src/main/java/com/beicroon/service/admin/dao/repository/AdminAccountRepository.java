package com.beicroon.service.admin.dao.repository;

import com.beicroon.construct.database.repository.GenericPageRepository;
import com.beicroon.construct.database.wrapper.IQueryWrapper;
import com.beicroon.provider.mybatisplus.repository.MybatisplusPageRepository;
import com.beicroon.service.admin.dao.mapper.AdminAccountMapper;
import com.beicroon.service.admin.dao.model.AdminAccountModel;
import com.beicroon.service.admin.dao.tab.AdminAccountTabHandler;
import com.beicroon.service.admin.entity.adminaccount.dto.AdminAccountQueryDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminAccountRepository extends MybatisplusPageRepository<AdminAccountQueryDTO, AdminAccountMapper, AdminAccountModel> implements GenericPageRepository<AdminAccountQueryDTO, AdminAccountModel> {

    @Resource
    private List<AdminAccountTabHandler> tabHandlers;

    @Override
    public List<AdminAccountTabHandler> getTabHandlers() {
        return this.tabHandlers;
    }

    @Override
    public IQueryWrapper<AdminAccountModel> fillQueryWrapper(AdminAccountQueryDTO dto, IQueryWrapper<AdminAccountModel> wrapper) {
        return wrapper;
    }

}
