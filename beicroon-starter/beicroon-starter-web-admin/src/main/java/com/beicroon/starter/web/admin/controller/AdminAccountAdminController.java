package com.beicroon.starter.web.admin.controller;

import com.beicroon.construct.annotation.ApiOperation;
import com.beicroon.construct.annotation.ApiTag;
import com.beicroon.construct.entity.IdsDTO;
import com.beicroon.construct.entity.Result;
import com.beicroon.construct.entity.WebResult;
import com.beicroon.starter.web.admin.entity.account.admin.dto.AccountAdminCreateDTO;
import com.beicroon.starter.web.admin.entity.account.admin.dto.AccountAdminQueryDTO;
import com.beicroon.starter.web.admin.entity.account.admin.dto.AccountAdminUpdateDTO;
import com.beicroon.starter.web.admin.entity.account.admin.role.dto.AccountAdminRoleAssignDTO;
import com.beicroon.starter.web.admin.entity.account.admin.vo.AccountAdminBaseVO;
import com.beicroon.starter.web.admin.entity.account.admin.vo.AccountAdminDetailVO;
import com.beicroon.starter.web.admin.entity.account.admin.vo.AccountAdminPageVO;
import com.beicroon.starter.web.admin.service.IAccountAdminService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@ApiTag(name = "后台账号相关")
@RequestMapping(path = "/api/admin/admin")
public class AdminAccountAdminController {

    @Resource
    private IAccountAdminService accountAdminService;

    @ApiOperation(name = "基础信息")
    @GetMapping(path = "/account-admin-show")
    public Result<AccountAdminBaseVO> show(@RequestParam("id") Long id) {
        return WebResult.success(this.accountAdminService.show(id));
    }

    @ApiOperation(name = "详细信息")
    @GetMapping(path = "/account-admin-detail")
    public Result<AccountAdminDetailVO> detail(@RequestParam("id") Long id) {
        return WebResult.success(this.accountAdminService.detail(id));
    }

    @ApiOperation(name = "分页列表")
    @PostMapping(path = "/account-admin-page")
    public Result<List<AccountAdminPageVO>> page(@RequestBody AccountAdminQueryDTO dto) {
        return WebResult.success(this.accountAdminService.page(dto));
    }

    @ApiOperation(name = "全量列表")
    @PostMapping(path = "/account-admin-list")
    public Result<List<AccountAdminBaseVO>> list(@RequestBody AccountAdminQueryDTO dto) {
        return WebResult.success(this.accountAdminService.list(dto));
    }

    @ApiOperation(name = "单个新增")
    @PostMapping(path = "/account-admin-create")
    public Result<Boolean> create(@RequestBody AccountAdminCreateDTO dto) {
        return WebResult.success(this.accountAdminService.create(dto));
    }

    @ApiOperation(name = "单个修改")
    @PutMapping(path = "/account-admin-update")
    public Result<Boolean> update(@RequestBody AccountAdminUpdateDTO dto) {
        return WebResult.success(this.accountAdminService.update(dto));
    }

    @ApiOperation(name = "批量删除")
    @DeleteMapping(path = "/account-admin-remove")
    public Result<Boolean> remove(@RequestBody IdsDTO dto) {
        return WebResult.success(this.accountAdminService.remove(dto));
    }

    @ApiOperation(name = "获取角色主键")
    @GetMapping(path = "/account-admin-role-id-list")
    public Result<List<Long>> roleIdList(@RequestParam("adminId") Long adminId) {
        return WebResult.success(this.accountAdminService.roleIdList(adminId));
    }

    @ApiOperation(name = "分配角色")
    @PostMapping(path = "/account-admin-role-assign")
    public Result<Boolean> roleAssign(@RequestBody AccountAdminRoleAssignDTO dto) {
        return WebResult.success(this.accountAdminService.roleAssign(dto));
    }

}
