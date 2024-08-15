package com.beicroon.service.admin.web.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beicroon.construct.entity.IdsDTO;
import com.beicroon.construct.entity.PageInfo;
import com.beicroon.construct.utils.ListUtils;
import com.beicroon.service.admin.dao.convertor.ResourceMenuConvertor;
import com.beicroon.service.admin.dao.model.ResourceMenuModel;
import com.beicroon.service.admin.dao.repository.ResourceMenuRepository;
import com.beicroon.service.admin.entity.resource.menu.dto.ResourceMenuCreateDTO;
import com.beicroon.service.admin.entity.resource.menu.dto.ResourceMenuQueryDTO;
import com.beicroon.service.admin.entity.resource.menu.dto.ResourceMenuUpdateDTO;
import com.beicroon.service.admin.entity.resource.menu.vo.ResourceMenuBaseVO;
import com.beicroon.service.admin.entity.resource.menu.vo.ResourceMenuDetailVO;
import com.beicroon.service.admin.entity.resource.menu.vo.ResourceMenuPageVO;
import com.beicroon.service.admin.web.service.IResourceMenuService;
import com.beicroon.starter.mysql.utils.PageUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceMenuService implements IResourceMenuService {

    @Resource
    private ResourceMenuConvertor resourceMenuConvertor;

    @Resource
    private ResourceMenuRepository resourceMenuRepository;

    @Override
    public ResourceMenuBaseVO show(Long id) {
        ResourceMenuModel resourceMenu = this.resourceMenuRepository.getById(id);

        return this.resourceMenuConvertor.toBaseVO(resourceMenu);
    }

    @Override
    public ResourceMenuDetailVO detail(Long id) {
        ResourceMenuModel resourceMenu = this.resourceMenuRepository.getById(id);
        
        return this.resourceMenuConvertor.toDetailVO(resourceMenu);
    }

    @Override
    public List<ResourceMenuBaseVO> list(ResourceMenuQueryDTO dto) {
        List<ResourceMenuModel> list = this.resourceMenuRepository.list(dto);
        
        return ListUtils.toList(list, this.resourceMenuConvertor::toBaseVO);
    }

    @Override
    public PageInfo<ResourceMenuPageVO> page(ResourceMenuQueryDTO dto) {
        Page<ResourceMenuModel> page = this.resourceMenuRepository.page(dto);
        
        return PageUtils.result(page, this.resourceMenuConvertor::toPageVO);
    }

    @Override
    public boolean create(ResourceMenuCreateDTO dto) {
        ResourceMenuModel creator = this.resourceMenuConvertor.toEntity(dto);
        
        return this.resourceMenuRepository.save(creator);
    }

    @Override
    public boolean update(ResourceMenuUpdateDTO dto) {
        ResourceMenuModel updater = this.resourceMenuConvertor.toEntity(dto);
        
        return this.resourceMenuRepository.updateById(updater);
    }

    @Override
    public boolean remove(IdsDTO dto) {
        return this.resourceMenuRepository.removeByIds(dto.getIds());
    }

}
