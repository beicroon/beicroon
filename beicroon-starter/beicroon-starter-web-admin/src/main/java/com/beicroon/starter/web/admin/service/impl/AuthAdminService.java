package com.beicroon.starter.web.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.beicroon.construct.auth.manager.AuthManager;
import com.beicroon.construct.auth.utils.AuthUtils;
import com.beicroon.construct.exception.utils.ExceptionUtils;
import com.beicroon.construct.jwt.utils.JwtUtils;
import com.beicroon.construct.utils.HashUtils;
import com.beicroon.construct.utils.ListUtils;
import com.beicroon.starter.web.admin.convertor.ResourceMenuConvertor;
import com.beicroon.starter.web.admin.entity.auth.admin.dto.AuthAdminLoginDTO;
import com.beicroon.starter.web.admin.entity.auth.admin.vo.AuthAdminLoginVO;
import com.beicroon.starter.web.admin.entity.auth.admin.vo.AuthAdminMenuVO;
import com.beicroon.starter.web.admin.helper.ResourceRoleHelper;
import com.beicroon.starter.web.admin.manager.ResourceRoleManager;
import com.beicroon.starter.web.admin.model.AccountAdminModel;
import com.beicroon.starter.web.admin.model.ResourceMenuModel;
import com.beicroon.starter.web.admin.model.ResourceRoleModel;
import com.beicroon.starter.web.admin.repository.AccountAdminRepository;
import com.beicroon.starter.web.admin.repository.ResourceMenuRepository;
import com.beicroon.starter.web.admin.service.IAuthAdminService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Service
public class AuthAdminService implements IAuthAdminService {

    @Resource
    private ResourceMenuConvertor resourceMenuConvertor;

    @Resource
    private AccountAdminRepository accountAdminRepository;

    @Resource
    private ResourceMenuRepository resourceMenuRepository;

    @Resource
    private ResourceRoleManager resourceRoleManager;

    @Resource
    private AuthManager authManager;

    @Override
    public AuthAdminLoginVO login(AuthAdminLoginDTO dto) {
        AccountAdminModel admin = this.accountAdminRepository.firstOrError(
                AccountAdminModel::getUsername, dto.getUsername(), "账号或密码错误"
        );

        if (!HashUtils.checkPassword(admin.getPassword(), dto.getPassword(), JwtUtils.getSaltString())) {
            throw ExceptionUtils.business("账号或密码错误");
        }

        AuthAdminLoginVO vo = new AuthAdminLoginVO();

        String token = JwtUtils.admin(admin);

        vo.setCode(admin.getCode());
        vo.setName(admin.getName());
        vo.setToken(token);

        this.authManager.addCache(admin.getId(), token);

        return vo;
    }

    @Override
    public boolean logout() {
        this.authManager.removeCache(AuthUtils.getUserId());

        return true;
    }

    @Override
    public List<AuthAdminMenuVO> menuTree() {
        List<ResourceRoleModel> roles = this.resourceRoleManager.listAuthUserRole();

        LambdaQueryWrapper<ResourceMenuModel> query = this.resourceMenuRepository.newLambdaQuery();

        if (ResourceRoleHelper.isNotRoot(roles)) {
            Set<Long> roleIds = ListUtils.toSet(roles, ResourceRoleModel::getId);

            Set<Long> menuIds = this.resourceRoleManager.listAuthUserRoleMenuId(roleIds);

            query.in(ResourceMenuModel::getId, menuIds);
        }

        query.isNull(ResourceMenuModel::getDisabledAt);
        query.orderByAsc(ResourceMenuModel::getSorting);

        List<ResourceMenuModel> list = this.resourceMenuRepository.list(query);

        List<AuthAdminMenuVO> tree = ListUtils.toTree(
                list,
                ResourceMenuModel::getId,
                ResourceMenuModel::getParentId,
                AuthAdminMenuVO::getChildren,
                this.resourceMenuConvertor::toTreeVO
        );

        tree.sort(Comparator.comparing(AuthAdminMenuVO::getSorting));

        return tree;
    }

}
