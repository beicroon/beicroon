package com.beicroon.service.admin.web.service.impl;

import com.beicroon.construct.constant.JwtConstant;
import com.beicroon.construct.exception.utils.ExceptionUtils;
import com.beicroon.construct.utils.HashUtils;
import com.beicroon.service.admin.dao.model.AccountAdminModel;
import com.beicroon.service.admin.dao.repository.AccountAdminRepository;
import com.beicroon.service.admin.entity.auth.admin.dto.AuthAdminLoginDTO;
import com.beicroon.service.admin.entity.auth.admin.vo.AuthAdminLoginVO;
import com.beicroon.service.admin.web.service.IAuthAdminService;
import com.beicroon.starter.jwt.utils.JwtUtils;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class AuthAdminService implements IAuthAdminService {

    @Resource
    private AccountAdminRepository accountAdminRepository;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

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

        this.redisTemplate.opsForValue().set(
                String.format(JwtConstant.ADMIN_LOGIN_KEY, admin.getId()), token
        );

        vo.setToken(token);

        return vo;
    }

}
