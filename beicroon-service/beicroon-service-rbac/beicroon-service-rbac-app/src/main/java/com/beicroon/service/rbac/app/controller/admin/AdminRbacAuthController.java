package com.beicroon.service.rbac.app.controller.admin;

import com.beicroon.construct.annotation.ApiOperation;
import com.beicroon.construct.annotation.ApiTag;
import com.beicroon.construct.base.entity.Result;
import com.beicroon.construct.base.entity.WebResult;
import com.beicroon.service.rbac.app.service.IRbacAuthService;
import com.beicroon.service.rbac.entity.rbacauth.dto.RbacAuthLoginDTO;
import com.beicroon.service.rbac.entity.rbacauth.vo.RbacAuthLoginVO;
import com.beicroon.service.rbac.entity.rbacauth.vo.RbacAuthResourceVO;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@ApiTag(name = "认证相关")
@RequestMapping(path = "/api/admin")
public class AdminRbacAuthController {

    @Resource
    private IRbacAuthService rbacAuthService;

    @ApiOperation(name = "登录认证")
    @PostMapping(path = "/rbac-auth-login")
    public Result<RbacAuthLoginVO> login(@Valid @RequestBody RbacAuthLoginDTO dto) {
        return WebResult.success(this.rbacAuthService.login(dto));
    }

    @ApiOperation(name = "获取资源")
    @GetMapping(path = "/rbac-auth-resource")
    public Result<List<RbacAuthResourceVO>> resource() {
        return WebResult.success(this.rbacAuthService.resource());
    }

    @ApiOperation(name = "注销认证")
    @PostMapping(path = "/rbac-auth-logout")
    public Result<Boolean> logout() {
        return WebResult.success(this.rbacAuthService.logout());
    }

}
