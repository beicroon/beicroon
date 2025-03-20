package com.beicroon.service.rbac.app.controller.admin;

import com.beicroon.construct.annotation.ApiOperation;
import com.beicroon.construct.annotation.ApiTag;
import com.beicroon.construct.base.entity.IdsDTO;
import com.beicroon.construct.base.entity.Result;
import com.beicroon.construct.base.entity.TabVO;
import com.beicroon.construct.base.entity.WebResult;
import com.beicroon.service.rbac.app.service.IAdminAccountService;
import com.beicroon.service.rbac.entity.adminaccount.dto.AdminAccountCreateDTO;
import com.beicroon.service.rbac.entity.adminaccount.dto.AdminAccountQueryDTO;
import com.beicroon.service.rbac.entity.adminaccount.dto.AdminAccountUpdateDTO;
import com.beicroon.service.rbac.entity.adminaccount.vo.AdminAccountBaseVO;
import com.beicroon.service.rbac.entity.adminaccount.vo.AdminAccountDetailVO;
import com.beicroon.service.rbac.entity.adminaccount.vo.AdminAccountPageVO;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@ApiTag(name = "账号相关")
@RequestMapping(path = "/api/admin")
public class AdminAdminAccountController {

    @Resource
    private IAdminAccountService adminAccountService;

    @ApiOperation(name = "分页统计")
    @PostMapping(path = "/admin-account-tab")
    public Result<List<TabVO>> tab(@Valid @RequestBody AdminAccountQueryDTO dto) {
        return WebResult.success(this.adminAccountService.tab(dto));
    }

    @ApiOperation(name = "全量列表")
    @PostMapping(path = "/admin-account-list")
    public Result<List<AdminAccountBaseVO>> list(@Valid @RequestBody AdminAccountQueryDTO dto) {
        return WebResult.success(this.adminAccountService.list(dto));
    }

    @ApiOperation(name = "分页列表")
    @PostMapping(path = "/admin-account-page")
    public Result<List<AdminAccountPageVO>> page(@Valid @RequestBody AdminAccountQueryDTO dto) {
        return WebResult.success(this.adminAccountService.page(dto));
    }

    @ApiOperation(name = "单个新增")
    @PostMapping(path = "/admin-account-create")
    public Result<Boolean> create(@Valid @RequestBody AdminAccountCreateDTO dto) {
        return WebResult.success(this.adminAccountService.create(dto));
    }

    @ApiOperation(name = "单个编辑")
    @PutMapping(path = "/admin-account-update")
    public Result<Boolean> update(@Valid @RequestBody AdminAccountUpdateDTO dto) {
        return WebResult.success(this.adminAccountService.update(dto));
    }

    @ApiOperation(name = "基础信息")
    @GetMapping(path = "/admin-account-show")
    public Result<AdminAccountBaseVO> show(@RequestParam("id") Long id) {
        return WebResult.success(this.adminAccountService.show(id));
    }

    @ApiOperation(name = "详细信息")
    @GetMapping(path = "/admin-account-detail")
    public Result<AdminAccountDetailVO> detail(@RequestParam("id") Long id) {
        return WebResult.success(this.adminAccountService.detail(id));
    }

    @ApiOperation(name = "批量删除")
    @DeleteMapping(path = "/admin-account-remove")
    public Result<Boolean> remove(@Valid @RequestBody IdsDTO dto) {
        return WebResult.success(this.adminAccountService.remove(dto));
    }

}
