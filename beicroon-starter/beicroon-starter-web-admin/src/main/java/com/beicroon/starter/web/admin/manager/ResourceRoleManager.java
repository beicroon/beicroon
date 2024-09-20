package com.beicroon.starter.web.admin.manager;

import com.beicroon.construct.auth.utils.AuthUtils;
import com.beicroon.construct.exception.utils.ExceptionUtils;
import com.beicroon.construct.utils.EmptyUtils;
import com.beicroon.starter.web.admin.convertor.ResourceRoleConvertor;
import com.beicroon.starter.web.admin.model.AccountAdminRoleModel;
import com.beicroon.starter.web.admin.model.ResourceMenuModel;
import com.beicroon.starter.web.admin.model.ResourceRoleMenuModel;
import com.beicroon.starter.web.admin.model.ResourceRoleModel;
import com.beicroon.starter.web.admin.repository.AccountAdminRoleRepository;
import com.beicroon.starter.web.admin.repository.ResourceMenuRepository;
import com.beicroon.starter.web.admin.repository.ResourceRoleMenuRepository;
import com.beicroon.starter.web.admin.repository.ResourceRoleRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Slf4j
@Component
public class ResourceRoleManager {

    @Resource
    private ResourceRoleConvertor resourceRoleConvertor;

    @Resource
    private ResourceRoleRepository resourceRoleRepository;

    @Resource
    private ResourceMenuRepository resourceMenuRepository;

    @Resource
    private AccountAdminRoleRepository accountAdminRoleRepository;

    @Resource
    private ResourceRoleMenuRepository resourceRoleMenuRepository;

    public List<ResourceRoleModel> listAuthUserRole() {
        Set<Long> roleIds = this.accountAdminRoleRepository.list(
                AccountAdminRoleModel::getAdminId, AuthUtils.getUserId(), AccountAdminRoleModel::getRoleId
        );

        List<ResourceRoleModel> roles = this.resourceRoleRepository.listByIdsAndEnable(roleIds);

        if (EmptyUtils.isEmpty(roles)) {
            throw ExceptionUtils.forbidden("账号未分配任何角色或角色已禁用");
        }

        return roles;
    }

    public Set<Long> listAuthUserRoleMenuId(Set<Long> roleIds) {
        Set<Long> menuIds = this.resourceRoleMenuRepository.list(
                ResourceRoleMenuModel::getRoleId, roleIds, ResourceRoleMenuModel::getMenuId
        );

        Set<Long> enableMenuIds = this.resourceMenuRepository.listByIdsAndEnable(
                menuIds, ResourceMenuModel::getId
        );

        if (EmptyUtils.isEmpty(enableMenuIds)) {
            throw ExceptionUtils.forbidden("角色未分配任何菜单");
        }

        return enableMenuIds;
    }

}
