package com.beicroon.service.rbac.app.controller.admin;

import com.beicroon.construct.annotation.ApiOperation;
import com.beicroon.construct.annotation.ApiTag;
import com.beicroon.construct.base.entity.IdsDTO;
import com.beicroon.construct.base.entity.Result;
import com.beicroon.construct.base.entity.TabVO;
import com.beicroon.construct.base.entity.WebResult;
import com.beicroon.service.rbac.app.service.IRbacAccountService;
import com.beicroon.service.rbac.entity.rbacaccount.dto.RbacAccountCreateDTO;
import com.beicroon.service.rbac.entity.rbacaccount.dto.RbacAccountQueryDTO;
import com.beicroon.service.rbac.entity.rbacaccount.dto.RbacAccountUpdateDTO;
import com.beicroon.service.rbac.entity.rbacaccount.vo.RbacAccountBaseVO;
import com.beicroon.service.rbac.entity.rbacaccount.vo.RbacAccountDetailVO;
import com.beicroon.service.rbac.entity.rbacaccount.vo.RbacAccountPageVO;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@ApiTag(name = "账号相关")
@RequestMapping(path = "/api/admin")
public class AdminRbacAccountController {

    @Resource
    private IRbacAccountService rbacAccountService;

    @ApiOperation(name = "分页统计")
    @PostMapping(path = "/rbac-account-tab")
    public Result<List<TabVO>> tab(@Valid @RequestBody RbacAccountQueryDTO dto) {
        return WebResult.success(this.rbacAccountService.tab(dto));
    }

    @ApiOperation(name = "全量列表")
    @PostMapping(path = "/rbac-account-list")
    public Result<List<RbacAccountBaseVO>> list(@Valid @RequestBody RbacAccountQueryDTO dto) {
        return WebResult.success(this.rbacAccountService.list(dto));
    }

    @ApiOperation(name = "分页列表")
    @PostMapping(path = "/rbac-account-page")
    public Result<List<RbacAccountPageVO>> page(@Valid @RequestBody RbacAccountQueryDTO dto) {
        return WebResult.success(this.rbacAccountService.page(dto));
    }

    @ApiOperation(name = "单个新增")
    @PostMapping(path = "/rbac-account-create")
    public Result<Boolean> create(@Valid @RequestBody RbacAccountCreateDTO dto) {
        return WebResult.success(this.rbacAccountService.create(dto));
    }

    @ApiOperation(name = "单个编辑")
    @PutMapping(path = "/rbac-account-update")
    public Result<Boolean> update(@Valid @RequestBody RbacAccountUpdateDTO dto) {
        return WebResult.success(this.rbacAccountService.update(dto));
    }

    @ApiOperation(name = "基础信息")
    @GetMapping(path = "/rbac-account-show")
    public Result<RbacAccountBaseVO> show(@RequestParam("id") Long id) {
        return WebResult.success(this.rbacAccountService.show(id));
    }

    @ApiOperation(name = "详细信息")
    @GetMapping(path = "/rbac-account-detail")
    public Result<RbacAccountDetailVO> detail(@RequestParam("id") Long id) {
        return WebResult.success(this.rbacAccountService.detail(id));
    }

    @ApiOperation(name = "批量删除")
    @DeleteMapping(path = "/rbac-account-remove")
    public Result<Boolean> remove(@Valid @RequestBody IdsDTO dto) {
        return WebResult.success(this.rbacAccountService.remove(dto));
    }

}
