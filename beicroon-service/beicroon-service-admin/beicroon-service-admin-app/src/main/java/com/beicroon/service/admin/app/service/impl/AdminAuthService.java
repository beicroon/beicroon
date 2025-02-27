package com.beicroon.service.admin.app.service.impl;

import com.beicroon.construct.auth.utils.AuthUtils;
import com.beicroon.construct.auth.utils.JwtUtils;
import com.beicroon.construct.exception.utils.ExceptionUtils;
import com.beicroon.construct.utils.HashUtils;
import com.beicroon.construct.utils.ListUtils;
import com.beicroon.service.admin.app.service.IAdminAuthService;
import com.beicroon.service.admin.dao.convertor.AdminAccountConvertor;
import com.beicroon.service.admin.dao.convertor.AdminResourceConvertor;
import com.beicroon.service.admin.dao.helper.AdminAccountHelper;
import com.beicroon.service.admin.dao.model.AdminAccountModel;
import com.beicroon.service.admin.dao.model.AdminAccountRoleModel;
import com.beicroon.service.admin.dao.model.AdminResourceModel;
import com.beicroon.service.admin.dao.model.AdminRoleResourceModel;
import com.beicroon.service.admin.dao.repository.AdminAccountRepository;
import com.beicroon.service.admin.dao.repository.AdminAccountRoleRepository;
import com.beicroon.service.admin.dao.repository.AdminResourceRepository;
import com.beicroon.service.admin.dao.repository.AdminRoleResourceRepository;
import com.beicroon.service.admin.entity.auth.dto.AdminAuthLoginDTO;
import com.beicroon.service.admin.entity.auth.vo.AdminAuthLoginVO;
import com.beicroon.service.admin.entity.auth.vo.AdminAuthResourceVO;
import com.beicroon.starter.auth.manager.AuthManager;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AdminAuthService implements IAdminAuthService {

    @Resource
    private AuthManager authManager;

    @Resource
    private AdminAccountConvertor adminAccountConvertor;

    @Resource
    private AdminResourceConvertor adminResourceConvertor;

    @Resource
    private AdminAccountRepository adminAccountRepository;

    @Resource
    private AdminResourceRepository adminResourceRepository;

    @Resource
    private AdminAccountRoleRepository adminAccountRoleRepository;

    @Resource
    private AdminRoleResourceRepository adminRoleResourceRepository;

    @Override
    public AdminAuthLoginVO login(AdminAuthLoginDTO dto) {
        AdminAccountModel model = this.adminAccountRepository.firstOrError(
                AdminAccountModel::getUsername, dto.getUsername(), "账号或密码错误"
        );

        if (!HashUtils.checkPassword(model.getPassword(), dto.getPassword(), AdminAccountHelper.AUTH_SALT)) {
            throw ExceptionUtils.business("账号或密码错误");
        }

        AdminAuthLoginVO vo = this.adminAccountConvertor.toAuthLoginVO(model, JwtUtils.admin(model));

        this.authManager.setCache(model.getId(), vo.getToken());

        return vo;
    }

    @Override
    public List<AdminAuthResourceVO> resource() {
        Set<Long> roleIds = this.adminAccountRoleRepository.list(
                AdminAccountRoleModel::getAccountId, AuthUtils.getUserId(), AdminAccountRoleModel::getRoleId
        );

        Set<Long> resourceIds = this.adminRoleResourceRepository.list(
                AdminRoleResourceModel::getRoleId, roleIds, AdminRoleResourceModel::getResourceId
        );

        List<AdminResourceModel> resources = this.adminResourceRepository.listByIds(resourceIds);

        return ListUtils.toTree(
                resources,
                AdminResourceModel::getId,
                AdminResourceModel::getParentId,
                AdminAuthResourceVO::getChildren,
                this.adminResourceConvertor::toAuthResourceVO
        );
    }

    @Override
    public Boolean logout() {
        this.authManager.deleteCache(AuthUtils.getUserId());

        return true;
    }

}
