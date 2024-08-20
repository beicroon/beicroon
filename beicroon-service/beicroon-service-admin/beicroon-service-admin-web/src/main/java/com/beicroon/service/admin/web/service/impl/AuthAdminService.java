package com.beicroon.service.admin.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.beicroon.construct.exception.utils.ExceptionUtils;
import com.beicroon.construct.utils.EmptyUtils;
import com.beicroon.construct.utils.HashUtils;
import com.beicroon.construct.utils.ListUtils;
import com.beicroon.service.admin.dao.convertor.ResourceMenuConvertor;
import com.beicroon.service.admin.dao.model.AccountAdminModel;
import com.beicroon.service.admin.dao.model.ResourceMenuModel;
import com.beicroon.service.admin.dao.repository.AccountAdminRepository;
import com.beicroon.service.admin.dao.repository.ResourceMenuRepository;
import com.beicroon.service.admin.entity.auth.admin.dto.AuthAdminLoginDTO;
import com.beicroon.service.admin.entity.auth.admin.vo.AuthAdminLoginVO;
import com.beicroon.service.admin.entity.auth.admin.vo.AuthAdminMenuVO;
import com.beicroon.service.admin.web.service.IAuthAdminService;
import com.beicroon.starter.jwt.utils.JwtUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Service
public class AuthAdminService implements IAuthAdminService {

    @Resource
    private ResourceMenuConvertor resourceMenuConvertor;

    @Resource
    private AccountAdminRepository accountAdminRepository;

    @Resource
    private ResourceMenuRepository resourceMenuRepository;

    @Override
    public AuthAdminLoginVO login(AuthAdminLoginDTO dto) {
        AccountAdminModel admin = this.accountAdminRepository.firstOrError(
                AccountAdminModel::getUsername, dto.getUsername(), "账号或密码错误"
        );

        if (!HashUtils.checkPassword(admin.getPassword(), dto.getPassword())) {
            throw ExceptionUtils.business("账号或密码错误");
        }

        AuthAdminLoginVO vo = new AuthAdminLoginVO();

        String token = JwtUtils.admin(admin);

        vo.setId(admin.getId());
        vo.setCode(admin.getCode());
        vo.setNickname(admin.getNickname());

        vo.setToken(token);

        return vo;
    }

    @Override
    public List<AuthAdminMenuVO> menuTree() {
        LambdaQueryWrapper<ResourceMenuModel> query = this.resourceMenuRepository.newLambdaQuery();

        query.isNull(ResourceMenuModel::getDisabledAt);

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

        for (AuthAdminMenuVO menu : parentMap.values()) {
            if (EmptyUtils.isNotId(menu.getParentId())) {
                continue;
            }

            AuthAdminMenuVO parent = parentMap.get(menu.getParentId());

            if (parent == null) {
                continue;
            }

            parent.getChildren().add(menu);
        }

        parents.sort(Comparator.comparing(AuthAdminMenuVO::getSorting).reversed());

        return parents;
    }

}
