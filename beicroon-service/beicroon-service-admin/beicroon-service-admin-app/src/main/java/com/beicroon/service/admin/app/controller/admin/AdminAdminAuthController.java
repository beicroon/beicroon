package com.beicroon.service.admin.app.controller.admin;

import com.beicroon.construct.annotation.ApiOperation;
import com.beicroon.construct.annotation.ApiTag;
import com.beicroon.construct.base.entity.Result;
import com.beicroon.construct.base.entity.WebResult;
import com.beicroon.service.admin.app.service.IAdminAuthService;
import com.beicroon.service.admin.entity.auth.dto.AdminAuthLoginDTO;
import com.beicroon.service.admin.entity.auth.vo.AdminAuthLoginVO;
import com.beicroon.service.admin.entity.auth.vo.AdminAuthResourceVO;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@ApiTag(name = "认证相关")
@RequestMapping(path = "/api/admin/admin")
public class AdminAdminAuthController {

    @Resource
    private IAdminAuthService adminAuthService;

    @ApiOperation(name = "登录认证")
    @PostMapping(path = "/admin-auth-login")
    public Result<AdminAuthLoginVO> login(@Valid @RequestBody AdminAuthLoginDTO dto) {
        return WebResult.success(this.adminAuthService.login(dto));
    }

    @ApiOperation(name = "获取资源")
    @GetMapping(path = "/admin-auth-resource")
    public Result<List<AdminAuthResourceVO>> resource() {
        return WebResult.success(this.adminAuthService.resource());
    }

    @ApiOperation(name = "注销认证")
    @PostMapping(path = "/admin-auth-logout")
    public Result<Boolean> logout() {
        return WebResult.success(this.adminAuthService.logout());
    }

}
