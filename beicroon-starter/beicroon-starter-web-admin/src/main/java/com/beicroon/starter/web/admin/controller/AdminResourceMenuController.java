package com.beicroon.starter.web.admin.controller;

import com.beicroon.construct.annotation.ApiOperation;
import com.beicroon.construct.annotation.ApiTag;
import com.beicroon.construct.entity.IdsDTO;
import com.beicroon.construct.entity.Result;
import com.beicroon.construct.entity.WebResult;
import com.beicroon.starter.web.admin.entity.resource.menu.dto.ResourceMenuCreateDTO;
import com.beicroon.starter.web.admin.entity.resource.menu.dto.ResourceMenuQueryDTO;
import com.beicroon.starter.web.admin.entity.resource.menu.dto.ResourceMenuUpdateDTO;
import com.beicroon.starter.web.admin.entity.resource.menu.vo.ResourceMenuBaseVO;
import com.beicroon.starter.web.admin.entity.resource.menu.vo.ResourceMenuDetailVO;
import com.beicroon.starter.web.admin.entity.resource.menu.vo.ResourceMenuPageVO;
import com.beicroon.starter.web.admin.service.IResourceMenuService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@ApiTag(name = "资源菜单相关")
@RequestMapping(path = "/api/admin/admin")
public class AdminResourceMenuController {

    @Resource
    private IResourceMenuService resourceMenuService;

    @ApiOperation(name = "基础信息")
    @GetMapping(path = "/resource-menu-show")
    public Result<ResourceMenuBaseVO> show(@RequestParam("id") Long id) {
        return WebResult.success(this.resourceMenuService.show(id));
    }

    @ApiOperation(name = "详细信息")
    @GetMapping(path = "/resource-menu-detail")
    public Result<ResourceMenuDetailVO> detail(@RequestParam("id") Long id) {
        return WebResult.success(this.resourceMenuService.detail(id));
    }

    @ApiOperation(name = "分页列表")
    @PostMapping(path = "/resource-menu-page")
    public Result<List<ResourceMenuPageVO>> page(@Valid @RequestBody ResourceMenuQueryDTO dto) {
        return WebResult.success(this.resourceMenuService.page(dto));
    }

    @ApiOperation(name = "全量列表")
    @PostMapping(path = "/resource-menu-list")
    public Result<List<ResourceMenuBaseVO>> list(@Valid @RequestBody ResourceMenuQueryDTO dto) {
        return WebResult.success(this.resourceMenuService.list(dto));
    }

    @ApiOperation(name = "单个新增")
    @PostMapping(path = "/resource-menu-create")
    public Result<Boolean> create(@Valid @RequestBody ResourceMenuCreateDTO dto) {
        return WebResult.success(this.resourceMenuService.create(dto));
    }

    @ApiOperation(name = "单个修改")
    @PutMapping(path = "/resource-menu-update")
    public Result<Boolean> update(@Valid @RequestBody ResourceMenuUpdateDTO dto) {
        return WebResult.success(this.resourceMenuService.update(dto));
    }

    @ApiOperation(name = "批量删除")
    @DeleteMapping(path = "/resource-menu-remove")
    public Result<Boolean> remove(@Valid @RequestBody IdsDTO dto) {
        return WebResult.success(this.resourceMenuService.remove(dto));
    }

}
