package com.beicroon.service.rbac.app.service.impl;

import com.beicroon.construct.auth.utils.AuthUtils;
import com.beicroon.construct.auth.utils.JwtUtils;
import com.beicroon.construct.exception.utils.ExceptionUtils;
import com.beicroon.construct.utils.HashUtils;
import com.beicroon.construct.utils.ListUtils;
import com.beicroon.service.rbac.app.service.IRbacAuthService;
import com.beicroon.service.rbac.dao.convertor.RbacAccountConvertor;
import com.beicroon.service.rbac.dao.convertor.RbacResourceConvertor;
import com.beicroon.service.rbac.dao.helper.RbacAccountHelper;
import com.beicroon.service.rbac.dao.model.RbacAccountModel;
import com.beicroon.service.rbac.dao.model.RbacAccountRoleModel;
import com.beicroon.service.rbac.dao.model.RbacResourceModel;
import com.beicroon.service.rbac.dao.model.RbacRoleResourceModel;
import com.beicroon.service.rbac.dao.repository.RbacAccountRepository;
import com.beicroon.service.rbac.dao.repository.RbacAccountRoleRepository;
import com.beicroon.service.rbac.dao.repository.RbacResourceRepository;
import com.beicroon.service.rbac.dao.repository.RbacRoleResourceRepository;
import com.beicroon.service.rbac.entity.rbacauth.dto.RbacAuthLoginDTO;
import com.beicroon.service.rbac.entity.rbacauth.vo.RbacAuthLoginVO;
import com.beicroon.service.rbac.entity.rbacauth.vo.RbacAuthResourceVO;
import com.beicroon.starter.auth.manager.AuthManager;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RbacAuthService implements IRbacAuthService {

    @Resource
    private AuthManager authManager;

    @Resource
    private RbacAccountConvertor rbacAccountConvertor;

    @Resource
    private RbacResourceConvertor rbacResourceConvertor;

    @Resource
    private RbacAccountRepository rbacAccountRepository;

    @Resource
    private RbacResourceRepository rbacResourceRepository;

    @Resource
    private RbacAccountRoleRepository rbacAccountRoleRepository;

    @Resource
    private RbacRoleResourceRepository rbacRoleResourceRepository;

    @Override
    public RbacAuthLoginVO login(RbacAuthLoginDTO dto) {
        RbacAccountModel model = this.rbacAccountRepository.firstOrError(
                RbacAccountModel::getUsername, dto.getUsername(), "账号或密码错误"
        );

        if (!HashUtils.checkPassword(model.getPassword(), dto.getPassword(), RbacAccountHelper.AUTH_SALT)) {
            throw ExceptionUtils.business("账号或密码错误");
        }

        RbacAuthLoginVO vo = this.rbacAccountConvertor.toAuthLoginVO(model, JwtUtils.admin(model));

        this.authManager.setCache(model.getId(), vo.getToken());

        return vo;
    }

    @Override
    public List<RbacAuthResourceVO> resource() {
        Set<Long> roleIds = this.rbacAccountRoleRepository.list(
                RbacAccountRoleModel::getAccountId, AuthUtils.getUserId(), RbacAccountRoleModel::getRoleId
        );

        Set<Long> resourceIds = this.rbacRoleResourceRepository.list(
                RbacRoleResourceModel::getRoleId, roleIds, RbacRoleResourceModel::getResourceId
        );

        List<RbacResourceModel> resources = this.rbacResourceRepository.listByIds(resourceIds);

        return ListUtils.toTree(
                resources,
                RbacResourceModel::getId,
                RbacResourceModel::getParentId,
                RbacAuthResourceVO::getChildren,
                this.rbacResourceConvertor::toAuthResourceVO
        );
    }

    @Override
    public Boolean logout() {
        this.authManager.deleteCache(AuthUtils.getUserId());

        return true;
    }

}
