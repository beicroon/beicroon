package com.beicroon.service.admin.app.service.impl;

import com.beicroon.construct.base.entity.IdsDTO;
import com.beicroon.construct.base.entity.PageInfo;
import com.beicroon.construct.base.entity.TabVO;
import com.beicroon.construct.utils.ListUtils;
import com.beicroon.service.admin.app.service.IAdminResourceService;
import com.beicroon.service.admin.dao.convertor.AdminResourceConvertor;
import com.beicroon.service.admin.dao.manager.AdminResourceManager;
import com.beicroon.service.admin.dao.model.AdminResourceModel;
import com.beicroon.service.admin.dao.repository.AdminResourceRepository;
import com.beicroon.service.admin.entity.adminresource.dto.AdminResourceCreateDTO;
import com.beicroon.service.admin.entity.adminresource.dto.AdminResourceQueryDTO;
import com.beicroon.service.admin.entity.adminresource.dto.AdminResourceUpdateDTO;
import com.beicroon.service.admin.entity.adminresource.vo.AdminResourceBaseVO;
import com.beicroon.service.admin.entity.adminresource.vo.AdminResourceDetailVO;
import com.beicroon.service.admin.entity.adminresource.vo.AdminResourcePageVO;
import com.beicroon.service.admin.entity.adminresource.vo.AdminResourceTreeVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class AdminResourceService implements IAdminResourceService {

    @Resource
    private AdminResourceManager adminResourceManager;

    @Resource
    private AdminResourceConvertor adminResourceConvertor;

    @Resource
    private AdminResourceRepository adminResourceRepository;

    @Override
    public List<TabVO> tab(AdminResourceQueryDTO dto) {
        return this.adminResourceRepository.tab(dto);
    }

    @Override
    public List<AdminResourceBaseVO> list(AdminResourceQueryDTO dto) {
        List<AdminResourceModel> list = this.adminResourceRepository.list(dto);

        return this.adminResourceConvertor.toBaseVO(list);
    }

    @Override
    public List<AdminResourceTreeVO> tree(AdminResourceQueryDTO dto) {
        List<AdminResourceModel> list = this.adminResourceRepository.list(dto);

        return ListUtils.toTree(
                list,
                AdminResourceModel::getId,
                AdminResourceModel::getParentId,
                AdminResourceTreeVO::getChildren,
                this.adminResourceConvertor::toTreeVO
        );
    }

    @Override
    public PageInfo<AdminResourcePageVO> page(AdminResourceQueryDTO dto) {
        PageInfo<AdminResourceModel> page = this.adminResourceRepository.page(dto);

        Set<Long> parentIds = ListUtils.toSet(page.getData(), AdminResourceModel::getParentId);

        Map<Long, AdminResourceModel> parentMap = ListUtils.toMap(
                this.adminResourceRepository.listByIds(parentIds),
                AdminResourceModel::getId
        );

        return this.adminResourceConvertor.toPageVO(page, (model, vo) -> {
            AdminResourceModel parent = parentMap.get(model.getParentId());

            if (parent != null) {
                vo.setParentCode(parent.getCode());
                vo.setParentName(parent.getName());
            }
        });
    }

    @Override
    public Boolean create(AdminResourceCreateDTO dto) {
        AdminResourceModel model = this.adminResourceConvertor.toCreator(dto);

        return this.adminResourceRepository.insert(model);
    }

    @Override
    public Boolean update(AdminResourceUpdateDTO dto) {
        AdminResourceModel model = this.adminResourceConvertor.toUpdater(dto);

        return this.adminResourceRepository.updateById(model);
    }

    @Override
    public AdminResourceBaseVO show(Long id) {
        AdminResourceModel model = this.adminResourceRepository.getById(id);

        return this.adminResourceConvertor.toBaseVO(model);
    }

    @Override
    public AdminResourceDetailVO detail(Long id) {
        AdminResourceModel model = this.adminResourceRepository.getById(id);

        return this.adminResourceConvertor.toDetailVO(model, vo -> {
            AdminResourceModel parent = this.adminResourceRepository.getById(vo.getParentId());

            if (parent != null) {
                vo.setParentCode(parent.getCode());
                vo.setParentName(parent.getName());
            }
        });
    }

    @Override
    public Boolean remove(IdsDTO dto) {
        return this.adminResourceRepository.removeByIds(dto.getIds());
    }

}
