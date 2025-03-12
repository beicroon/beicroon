package com.beicroon.service.rbac.app.controller.admin;

import com.beicroon.construct.annotation.ApiOperation;
import com.beicroon.construct.annotation.ApiTag;
import com.beicroon.construct.base.entity.IdsDTO;
import com.beicroon.construct.base.entity.Result;
import com.beicroon.construct.base.entity.TabVO;
import com.beicroon.construct.base.entity.WebResult;
import com.beicroon.service.rbac.app.service.IAdminRoleService;
import com.beicroon.service.rbac.entity.adminrole.dto.AdminRoleCreateDTO;
import com.beicroon.service.rbac.entity.adminrole.dto.AdminRoleQueryDTO;
import com.beicroon.service.rbac.entity.adminrole.dto.AdminRoleUpdateDTO;
import com.beicroon.service.rbac.entity.adminrole.vo.AdminRoleBaseVO;
import com.beicroon.service.rbac.entity.adminrole.vo.AdminRoleDetailVO;
import com.beicroon.service.rbac.entity.adminrole.vo.AdminRolePageVO;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@ApiTag(name = "角色相关")
@RequestMapping(path = "/api/admin/admin")
public class AdminAdminRoleController {

    @Resource
    private IAdminRoleService adminRoleService;

    @ApiOperation(name = "分页统计")
    @PostMapping(path = "/admin-role-tab")
    public Result<List<TabVO>> tab(@Valid @RequestBody AdminRoleQueryDTO dto) {
        return WebResult.success(this.adminRoleService.tab(dto));
    }

    @ApiOperation(name = "全量列表")
    @PostMapping(path = "/admin-role-list")
    public Result<List<AdminRoleBaseVO>> list(@Valid @RequestBody AdminRoleQueryDTO dto) {
        return WebResult.success(this.adminRoleService.list(dto));
    }

    @ApiOperation(name = "分页列表")
    @PostMapping(path = "/admin-role-page")
    public Result<List<AdminRolePageVO>> page(@Valid @RequestBody AdminRoleQueryDTO dto) {
        return WebResult.success(this.adminRoleService.page(dto));
    }

    @ApiOperation(name = "单个新增")
    @PostMapping(path = "/admin-role-create")
    public Result<Boolean> create(@Valid @RequestBody AdminRoleCreateDTO dto) {
        return WebResult.success(this.adminRoleService.create(dto));
    }

    @ApiOperation(name = "单个编辑")
    @PutMapping(path = "/admin-role-update")
    public Result<Boolean> update(@Valid @RequestBody AdminRoleUpdateDTO dto) {
        return WebResult.success(this.adminRoleService.update(dto));
    }

    @ApiOperation(name = "基础信息")
    @GetMapping(path = "/admin-role-show")
    public Result<AdminRoleBaseVO> show(@RequestParam("id") Long id) {
        return WebResult.success(this.adminRoleService.show(id));
    }

    @ApiOperation(name = "详细信息")
    @GetMapping(path = "/admin-role-detail")
    public Result<AdminRoleDetailVO> detail(@RequestParam("id") Long id) {
        return WebResult.success(this.adminRoleService.detail(id));
    }

    @ApiOperation(name = "批量删除")
    @DeleteMapping(path = "/admin-role-remove")
    public Result<Boolean> remove(@Valid @RequestBody IdsDTO dto) {
        return WebResult.success(this.adminRoleService.remove(dto));
    }

}
