package com.beicroon.service.admin.controller.admin;

import com.beicroon.construct.annotation.ApiOperation;
import com.beicroon.construct.annotation.ApiTag;
import com.beicroon.construct.entity.Result;
import com.beicroon.construct.entity.WebResult;
import com.beicroon.service.admin.entity.account.admin.dto.AccountAdminQueryDTO;
import com.beicroon.service.admin.entity.account.admin.vo.AccountAdminBaseVO;
import com.beicroon.service.admin.entity.account.admin.vo.AccountAdminDetailVO;
import com.beicroon.service.admin.entity.account.admin.vo.AccountAdminPageVO;
import com.beicroon.service.admin.service.impl.AccountAdminService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ApiTag("账号相关")
@RequestMapping("/api/admin/admin")
public class AdminAccountAdminController {

    @Resource
    private AccountAdminService accountAdminService;

    @ApiOperation("基础信息")
    @GetMapping("/account-admin-show")
    public Result<AccountAdminBaseVO> show(@RequestParam("id") Long id) {
        return WebResult.success(this.accountAdminService.show(id));
    }

    @ApiOperation("详细信息")
    @GetMapping("/account-admin-detail")
    public Result<AccountAdminDetailVO> detail(@RequestParam("id") Long id) {
        return WebResult.success(this.accountAdminService.detail(id));
    }

    @ApiOperation("全量列表")
    @PostMapping("/account-admin-list")
    public Result<List<AccountAdminBaseVO>> list(@RequestBody AccountAdminQueryDTO dto) {
        return WebResult.success(this.accountAdminService.list(dto));
    }

    @ApiOperation("分页列表")
    @PostMapping("/account-admin-page")
    public Result<List<AccountAdminPageVO>> page(@RequestBody AccountAdminQueryDTO dto) {
        return WebResult.success(this.accountAdminService.page(dto));
    }

}
