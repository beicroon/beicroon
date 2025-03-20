package com.beicroon.service.rbac.app.controller.admin;

import com.beicroon.construct.annotation.ApiOperation;
import com.beicroon.construct.annotation.ApiTag;
import com.beicroon.construct.base.entity.IdsDTO;
import com.beicroon.construct.base.entity.Result;
import com.beicroon.construct.base.entity.TabVO;
import com.beicroon.construct.base.entity.WebResult;
import com.beicroon.service.rbac.app.service.IRbacRoleService;
import com.beicroon.service.rbac.entity.rbacrole.dto.RbacRoleCreateDTO;
import com.beicroon.service.rbac.entity.rbacrole.dto.RbacRoleQueryDTO;
import com.beicroon.service.rbac.entity.rbacrole.dto.RbacRoleUpdateDTO;
import com.beicroon.service.rbac.entity.rbacrole.vo.RbacRoleBaseVO;
import com.beicroon.service.rbac.entity.rbacrole.vo.RbacRoleDetailVO;
import com.beicroon.service.rbac.entity.rbacrole.vo.RbacRolePageVO;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@ApiTag(name = "角色相关")
@RequestMapping(path = "/api/admin")
public class AdminRbacRoleController {

    @Resource
    private IRbacRoleService rbacRoleService;

    @ApiOperation(name = "分页统计")
    @PostMapping(path = "/rbac-role-tab")
    public Result<List<TabVO>> tab(@Valid @RequestBody RbacRoleQueryDTO dto) {
        return WebResult.success(this.rbacRoleService.tab(dto));
    }

    @ApiOperation(name = "全量列表")
    @PostMapping(path = "/rbac-role-list")
    public Result<List<RbacRoleBaseVO>> list(@Valid @RequestBody RbacRoleQueryDTO dto) {
        return WebResult.success(this.rbacRoleService.list(dto));
    }

    @ApiOperation(name = "分页列表")
    @PostMapping(path = "/rbac-role-page")
    public Result<List<RbacRolePageVO>> page(@Valid @RequestBody RbacRoleQueryDTO dto) {
        return WebResult.success(this.rbacRoleService.page(dto));
    }

    @ApiOperation(name = "单个新增")
    @PostMapping(path = "/rbac-role-create")
    public Result<Boolean> create(@Valid @RequestBody RbacRoleCreateDTO dto) {
        return WebResult.success(this.rbacRoleService.create(dto));
    }

    @ApiOperation(name = "单个编辑")
    @PutMapping(path = "/rbac-role-update")
    public Result<Boolean> update(@Valid @RequestBody RbacRoleUpdateDTO dto) {
        return WebResult.success(this.rbacRoleService.update(dto));
    }

    @ApiOperation(name = "基础信息")
    @GetMapping(path = "/rbac-role-show")
    public Result<RbacRoleBaseVO> show(@RequestParam("id") Long id) {
        return WebResult.success(this.rbacRoleService.show(id));
    }

    @ApiOperation(name = "详细信息")
    @GetMapping(path = "/rbac-role-detail")
    public Result<RbacRoleDetailVO> detail(@RequestParam("id") Long id) {
        return WebResult.success(this.rbacRoleService.detail(id));
    }

    @ApiOperation(name = "批量删除")
    @DeleteMapping(path = "/rbac-role-remove")
    public Result<Boolean> remove(@Valid @RequestBody IdsDTO dto) {
        return WebResult.success(this.rbacRoleService.remove(dto));
    }

}
