package com.beicroon.module.base.dao.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.beicroon.module.base.dao.mapper.BaseConfigMapper;
import com.beicroon.module.base.dao.model.BaseConfigModel;
import com.beicroon.module.base.dao.tab.BaseConfigTabHandler;
import com.beicroon.module.base.entity.baseconfig.dto.BaseConfigQueryDTO;
import com.beicroon.starter.dao.repository.GenericPageRepository;
import com.beicroon.starter.dao.repository.impl.MybatisplusPageRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BaseConfigRepository extends MybatisplusPageRepository<BaseConfigQueryDTO, BaseConfigMapper, BaseConfigModel> implements GenericPageRepository<BaseConfigQueryDTO, BaseConfigModel> {

    @Resource
    private List<BaseConfigTabHandler> tabHandlers;

    @Override
    public List<BaseConfigTabHandler> getTabHandlers() {
        return this.tabHandlers;
    }

    @Override
    public void fillQueryWrapper(BaseConfigQueryDTO dto, QueryWrapper<BaseConfigModel> wrapper) {

    }

}
