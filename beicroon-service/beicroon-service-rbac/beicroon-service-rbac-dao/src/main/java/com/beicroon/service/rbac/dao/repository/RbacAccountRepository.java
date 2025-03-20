package com.beicroon.service.rbac.dao.repository;

import com.beicroon.construct.database.repository.GenericPageRepository;
import com.beicroon.construct.database.wrapper.IQueryWrapper;
import com.beicroon.provider.mybatisplus.repository.MybatisplusPageRepository;
import com.beicroon.service.rbac.dao.mapper.RbacAccountMapper;
import com.beicroon.service.rbac.dao.model.RbacAccountModel;
import com.beicroon.service.rbac.dao.tab.RbacAccountTabHandler;
import com.beicroon.service.rbac.entity.rbacaccount.dto.RbacAccountQueryDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RbacAccountRepository extends MybatisplusPageRepository<RbacAccountQueryDTO, RbacAccountMapper, RbacAccountModel> implements GenericPageRepository<RbacAccountQueryDTO, RbacAccountModel> {

    @Resource
    private List<RbacAccountTabHandler> tabHandlers;

    @Override
    public List<RbacAccountTabHandler> getTabHandlers() {
        return this.tabHandlers;
    }

    @Override
    public IQueryWrapper<RbacAccountModel> fillQueryWrapper(RbacAccountQueryDTO dto, IQueryWrapper<RbacAccountModel> wrapper) {
        return wrapper;
    }

}
