package com.beicroon.starter.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beicroon.construct.entity.IdsDTO;
import com.beicroon.construct.entity.PageInfo;
import com.beicroon.construct.utils.ListUtils;
import com.beicroon.starter.mysql.utils.PageUtils;
import com.beicroon.starter.web.admin.convertor.ResourceMenuConvertor;
import com.beicroon.starter.web.admin.entity.resource.menu.dto.ResourceMenuCreateDTO;
import com.beicroon.starter.web.admin.entity.resource.menu.dto.ResourceMenuQueryDTO;
import com.beicroon.starter.web.admin.entity.resource.menu.dto.ResourceMenuUpdateDTO;
import com.beicroon.starter.web.admin.entity.resource.menu.vo.ResourceMenuBaseVO;
import com.beicroon.starter.web.admin.entity.resource.menu.vo.ResourceMenuDetailVO;
import com.beicroon.starter.web.admin.entity.resource.menu.vo.ResourceMenuPageVO;
import com.beicroon.starter.web.admin.manager.ResourceMenuManager;
import com.beicroon.starter.web.admin.model.ResourceMenuModel;
import com.beicroon.starter.web.admin.repository.ResourceMenuRepository;
import com.beicroon.starter.web.admin.service.IResourceMenuService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceMenuService implements IResourceMenuService {

    @Resource
    private ResourceMenuManager resourceMenuManager;

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

        this.resourceMenuManager.setParent(creator, dto.getParentId());

        return this.resourceMenuRepository.save(creator);
    }

    @Override
    public boolean update(ResourceMenuUpdateDTO dto) {
        ResourceMenuModel updater = this.resourceMenuConvertor.toEntity(dto);

        this.resourceMenuManager.setParent(updater, dto.getParentId());

        return this.resourceMenuRepository.updateById(updater);
    }

    @Override
    public boolean remove(IdsDTO dto) {
        return this.resourceMenuRepository.removeByIds(dto.getIds());
    }

}
