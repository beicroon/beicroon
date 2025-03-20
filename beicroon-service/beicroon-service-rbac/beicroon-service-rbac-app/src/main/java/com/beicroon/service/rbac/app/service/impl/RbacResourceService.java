package com.beicroon.service.rbac.app.service.impl;

import com.beicroon.construct.base.entity.IdsDTO;
import com.beicroon.construct.base.entity.PageInfo;
import com.beicroon.construct.base.entity.TabVO;
import com.beicroon.construct.utils.ListUtils;
import com.beicroon.service.rbac.app.service.IRbacResourceService;
import com.beicroon.service.rbac.dao.convertor.RbacResourceConvertor;
import com.beicroon.service.rbac.dao.manager.RbacResourceManager;
import com.beicroon.service.rbac.dao.model.RbacResourceModel;
import com.beicroon.service.rbac.dao.repository.RbacResourceRepository;
import com.beicroon.service.rbac.entity.rbacresource.dto.RbacResourceCreateDTO;
import com.beicroon.service.rbac.entity.rbacresource.dto.RbacResourceQueryDTO;
import com.beicroon.service.rbac.entity.rbacresource.dto.RbacResourceUpdateDTO;
import com.beicroon.service.rbac.entity.rbacresource.vo.RbacResourceBaseVO;
import com.beicroon.service.rbac.entity.rbacresource.vo.RbacResourceDetailVO;
import com.beicroon.service.rbac.entity.rbacresource.vo.RbacResourcePageVO;
import com.beicroon.service.rbac.entity.rbacresource.vo.RbacResourceTreeVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class RbacResourceService implements IRbacResourceService {

    @Resource
    private RbacResourceManager rbacResourceManager;

    @Resource
    private RbacResourceConvertor rbacResourceConvertor;

    @Resource
    private RbacResourceRepository rbacResourceRepository;

    @Override
    public List<TabVO> tab(RbacResourceQueryDTO dto) {
        return this.rbacResourceRepository.tab(dto);
    }

    @Override
    public List<RbacResourceBaseVO> list(RbacResourceQueryDTO dto) {
        List<RbacResourceModel> list = this.rbacResourceRepository.list(dto);

        return this.rbacResourceConvertor.toBaseVO(list);
    }

    @Override
    public List<RbacResourceTreeVO> tree(RbacResourceQueryDTO dto) {
        List<RbacResourceModel> list = this.rbacResourceRepository.list(dto);

        return ListUtils.toTree(
                list,
                RbacResourceModel::getId,
                RbacResourceModel::getParentId,
                RbacResourceTreeVO::getChildren,
                this.rbacResourceConvertor::toTreeVO
        );
    }

    @Override
    public PageInfo<RbacResourcePageVO> page(RbacResourceQueryDTO dto) {
        PageInfo<RbacResourceModel> page = this.rbacResourceRepository.page(dto);

        Set<Long> parentIds = ListUtils.toSet(page.getData(), RbacResourceModel::getParentId);

        Map<Long, RbacResourceModel> parentMap = ListUtils.toMap(
                this.rbacResourceRepository.listByIds(parentIds),
                RbacResourceModel::getId
        );

        return this.rbacResourceConvertor.toPageVO(page, (model, vo) -> {
            RbacResourceModel parent = parentMap.get(model.getParentId());

            if (parent != null) {
                vo.setParentCode(parent.getCode());
                vo.setParentName(parent.getName());
            }
        });
    }

    @Override
    public Boolean create(RbacResourceCreateDTO dto) {
        RbacResourceModel model = this.rbacResourceConvertor.toCreator(dto);

        return this.rbacResourceRepository.insert(model);
    }

    @Override
    public Boolean update(RbacResourceUpdateDTO dto) {
        RbacResourceModel model = this.rbacResourceConvertor.toUpdater(dto);

        return this.rbacResourceRepository.updateById(model);
    }

    @Override
    public RbacResourceBaseVO show(Long id) {
        RbacResourceModel model = this.rbacResourceRepository.getById(id);

        return this.rbacResourceConvertor.toBaseVO(model);
    }

    @Override
    public RbacResourceDetailVO detail(Long id) {
        RbacResourceModel model = this.rbacResourceRepository.getById(id);

        return this.rbacResourceConvertor.toDetailVO(model, vo -> {
            RbacResourceModel parent = this.rbacResourceRepository.getById(vo.getParentId());

            if (parent != null) {
                vo.setParentCode(parent.getCode());
                vo.setParentName(parent.getName());
            }
        });
    }

    @Override
    public Boolean remove(IdsDTO dto) {
        return this.rbacResourceRepository.removeByIds(dto.getIds());
    }

}
