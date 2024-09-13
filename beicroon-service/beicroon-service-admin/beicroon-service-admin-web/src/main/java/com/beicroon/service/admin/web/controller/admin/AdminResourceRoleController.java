package com.beicroon.service.admin.web.controller.admin;

import com.beicroon.construct.annotation.ApiOperation;
import com.beicroon.construct.annotation.ApiTag;
import com.beicroon.construct.entity.IdsDTO;
import com.beicroon.construct.entity.Result;
import com.beicroon.construct.entity.WebResult;
import com.beicroon.service.admin.entity.resource.role.dto.ResourceRoleCreateDTO;
import com.beicroon.service.admin.entity.resource.role.dto.ResourceRoleQueryDTO;
import com.beicroon.service.admin.entity.resource.role.dto.ResourceRoleUpdateDTO;
import com.beicroon.service.admin.entity.resource.role.vo.ResourceRoleBaseVO;
import com.beicroon.service.admin.entity.resource.role.vo.ResourceRoleDetailVO;
import com.beicroon.service.admin.entity.resource.role.vo.ResourceRolePageVO;
import com.beicroon.service.admin.web.service.IResourceRoleService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@ApiTag(name = "资源角色相关")
@RequestMapping(path = "/api/admin/admin")
public class AdminResourceRoleController {

    @Resource
    private IResourceRoleService resourceRoleService;

    @ApiOperation(name = "基础信息")
    @GetMapping(path = "/resource-role-show")
    public Result<ResourceRoleBaseVO> show(@RequestParam("id") Long id) {
        return WebResult.success(this.resourceRoleService.show(id));
    }

    @ApiOperation(name = "详细信息")
    @GetMapping(path = "/resource-role-detail")
    public Result<ResourceRoleDetailVO> detail(@RequestParam("id") Long id) {
        return WebResult.success(this.resourceRoleService.detail(id));
    }

    @ApiOperation(name = "分页列表")
    @PostMapping(path = "/resource-role-page")
    public Result<List<ResourceRolePageVO>> page(@RequestBody ResourceRoleQueryDTO dto) {
        return WebResult.success(this.resourceRoleService.page(dto));
    }

    @ApiOperation(name = "全量列表")
    @PostMapping(path = "/resource-role-list")
    public Result<List<ResourceRoleBaseVO>> list(@RequestBody ResourceRoleQueryDTO dto) {
        return WebResult.success(this.resourceRoleService.list(dto));
    }

    @ApiOperation(name = "单个新增")
    @PostMapping(path = "/resource-role-create")
    public Result<Boolean> create(@RequestBody ResourceRoleCreateDTO dto) {
        return WebResult.success(this.resourceRoleService.create(dto));
    }

    @ApiOperation(name = "单个修改")
    @PutMapping(path = "/resource-role-update")
    public Result<Boolean> update(@RequestBody ResourceRoleUpdateDTO dto) {
        return WebResult.success(this.resourceRoleService.update(dto));
    }

    @ApiOperation(name = "批量删除")
    @DeleteMapping(path = "/resource-role-remove")
    public Result<Boolean> remove(@RequestBody IdsDTO dto) {
        return WebResult.success(this.resourceRoleService.remove(dto));
    }

}
