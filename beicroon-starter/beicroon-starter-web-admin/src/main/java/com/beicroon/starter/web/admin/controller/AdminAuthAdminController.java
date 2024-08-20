package com.beicroon.starter.web.admin.controller;

import com.beicroon.construct.annotation.ApiOperation;
import com.beicroon.construct.annotation.ApiTag;
import com.beicroon.construct.entity.Result;
import com.beicroon.construct.entity.WebResult;
import com.beicroon.starter.web.admin.entity.auth.admin.dto.AuthAdminLoginDTO;
import com.beicroon.starter.web.admin.entity.auth.admin.vo.AuthAdminLoginVO;
import com.beicroon.starter.web.admin.entity.auth.admin.vo.AuthAdminMenuVO;
import com.beicroon.starter.web.admin.service.IAuthAdminService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ApiTag(name = "认证相关")
@RequestMapping("/api/admin/admin")
public class AdminAuthAdminController {

    @Resource
    private IAuthAdminService authAdminService;

    @ApiOperation(name = "登录认证")
    @PostMapping("/auth-admin-login")
    public Result<AuthAdminLoginVO> login(@Validated @RequestBody AuthAdminLoginDTO dto) {
        return WebResult.success(this.authAdminService.login(dto));
    }

    @ApiOperation(name = "菜单认证")
    @GetMapping("/auth-menu-tree")
    public Result<List<AuthAdminMenuVO>> menuTree() {
        return WebResult.success(this.authAdminService.menuTree());
    }

}
