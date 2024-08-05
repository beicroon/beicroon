package com.beicroon.service.admin.web.controller.admin;

import com.beicroon.construct.annotation.ApiOperation;
import com.beicroon.construct.annotation.ApiTag;
import com.beicroon.construct.entity.Result;
import com.beicroon.construct.entity.WebResult;
import com.beicroon.service.admin.entity.auth.admin.dto.AuthAdminLoginDTO;
import com.beicroon.service.admin.entity.auth.admin.vo.AuthAdminLoginVO;
import com.beicroon.service.admin.web.service.IAuthAdminService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ApiTag("认证相关")
@RequestMapping("/api/admin/admin")
public class AdminAuthAdminController {

    @Resource
    private IAuthAdminService authAdminService;

    @ApiOperation("登录认证")
    @PostMapping("/auth-admin-login")
    public Result<AuthAdminLoginVO> login(@Validated @RequestBody AuthAdminLoginDTO dto) {
        return WebResult.success(this.authAdminService.login(dto));
    }

}
