package com.beicroon.starter.web.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.beicroon.construct.auth.utils.AuthUtils;
import com.beicroon.construct.exception.utils.ExceptionUtils;
import com.beicroon.construct.utils.EmptyUtils;
import com.beicroon.construct.utils.HashUtils;
import com.beicroon.construct.utils.ListUtils;
import com.beicroon.starter.jwt.utils.JwtUtils;
import com.beicroon.starter.web.admin.convertor.ResourceMenuConvertor;
import com.beicroon.starter.web.admin.entity.auth.admin.dto.AuthAdminLoginDTO;
import com.beicroon.starter.web.admin.entity.auth.admin.vo.AuthAdminLoginVO;
import com.beicroon.starter.web.admin.entity.auth.admin.vo.AuthAdminMenuVO;
import com.beicroon.starter.web.admin.helper.ResourceRoleHelper;
import com.beicroon.starter.web.admin.model.*;
import com.beicroon.starter.web.admin.repository.*;
import com.beicroon.starter.web.admin.service.IAuthAdminService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthAdminService implements IAuthAdminService {

    @Resource
    private ResourceMenuConvertor resourceMenuConvertor;

    @Resource
    private AccountAdminRepository accountAdminRepository;

    @Resource
    private ResourceMenuRepository resourceMenuRepository;

    @Resource
    private ResourceRoleRepository resourceRoleRepository;

    @Resource
    private AccountAdminRoleRepository accountAdminRoleRepository;

    @Resource
    private ResourceRoleMenuRepository resourceRoleMenuRepository;

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
        vo.setNickname(admin.getNickname());
        vo.setToken(token);

        return vo;
    }

    @Override
    public List<AuthAdminMenuVO> menuTree() {
        Set<Long> roleIds = this.accountAdminRoleRepository.list(
                AccountAdminRoleModel::getAdminId, AuthUtils.getUserId(), AccountAdminRoleModel::getRoleId
        );

        if (EmptyUtils.isEmpty(roleIds)) {
            throw ExceptionUtils.forbidden("账号未分配任何角色");
        }

        List<ResourceRoleModel> roles = this.resourceRoleRepository.listByIdsAndEnable(roleIds);

        LambdaQueryWrapper<ResourceMenuModel> query = this.resourceMenuRepository.newLambdaQuery();

        // 不是超管
        if (roles.stream().noneMatch(ResourceRoleHelper::isRoot)) {
            Set<Long> menuIds = this.resourceRoleMenuRepository.list(
                    ResourceRoleMenuModel::getRoleId, roleIds, ResourceRoleMenuModel::getMenuId
            );

            if (EmptyUtils.isEmpty(menuIds)) {
                throw ExceptionUtils.forbidden("角色未分配任何菜单");
            }

            query.in(ResourceMenuModel::getId, menuIds);
        }

        query.isNull(ResourceMenuModel::getDisabledAt);
        query.orderByAsc(ResourceMenuModel::getSorting);

        List<ResourceMenuModel> list = this.resourceMenuRepository.list(query);

        List<AuthAdminMenuVO> parents = new ArrayList<>();

        Map<Long, AuthAdminMenuVO> parentMap = ListUtils.toMap(
                list, ResourceMenuModel::getId, item -> {
                    AuthAdminMenuVO vo = this.resourceMenuConvertor.toTreeVO(item);

                    if (EmptyUtils.isNotId(item.getParentId())) {
                        parents.add(vo);
                    }

                    return vo;
                }
        );

        for (ResourceMenuModel menu : list) {
            if (EmptyUtils.isNotId(menu.getParentId())) {
                continue;
            }

            AuthAdminMenuVO parent = parentMap.get(menu.getParentId());

            if (parent == null) {
                continue;
            }

            parent.getChildren().add(parentMap.get(menu.getId()));
        }

        parents.sort(Comparator.comparing(AuthAdminMenuVO::getSorting));

        return parents;
    }

}
