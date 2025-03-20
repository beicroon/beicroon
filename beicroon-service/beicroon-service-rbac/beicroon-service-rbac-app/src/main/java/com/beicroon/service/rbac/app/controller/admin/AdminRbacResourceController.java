package com.beicroon.service.rbac.app.controller.admin;

import com.beicroon.construct.annotation.ApiOperation;
import com.beicroon.construct.annotation.ApiTag;
import com.beicroon.construct.base.entity.IdsDTO;
import com.beicroon.construct.base.entity.Result;
import com.beicroon.construct.base.entity.TabVO;
import com.beicroon.construct.base.entity.WebResult;
import com.beicroon.service.rbac.app.service.IRbacResourceService;
import com.beicroon.service.rbac.entity.rbacresource.dto.RbacResourceCreateDTO;
import com.beicroon.service.rbac.entity.rbacresource.dto.RbacResourceQueryDTO;
import com.beicroon.service.rbac.entity.rbacresource.dto.RbacResourceUpdateDTO;
import com.beicroon.service.rbac.entity.rbacresource.vo.RbacResourceBaseVO;
import com.beicroon.service.rbac.entity.rbacresource.vo.RbacResourceDetailVO;
import com.beicroon.service.rbac.entity.rbacresource.vo.RbacResourcePageVO;
import com.beicroon.service.rbac.entity.rbacresource.vo.RbacResourceTreeVO;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@ApiTag(name = "资源相关")
@RequestMapping(path = "/api/admin")
public class AdminRbacResourceController {

    @Resource
    private IRbacResourceService rbacResourceService;

    @ApiOperation(name = "分页统计")
    @PostMapping(path = "/rbac-resource-tab")
    public Result<List<TabVO>> tab(@Valid @RequestBody RbacResourceQueryDTO dto) {
        return WebResult.success(this.rbacResourceService.tab(dto));
    }

    @ApiOperation(name = "全量列表")
    @PostMapping(path = "/rbac-resource-list")
    public Result<List<RbacResourceBaseVO>> list(@Valid @RequestBody RbacResourceQueryDTO dto) {
        return WebResult.success(this.rbacResourceService.list(dto));
    }

    @ApiOperation(name = "树状列表")
    @PostMapping(path = "/rbac-resource-tree")
    public Result<List<RbacResourceTreeVO>> tree(@Valid @RequestBody RbacResourceQueryDTO dto) {
        return WebResult.success(this.rbacResourceService.tree(dto));
    }

    @ApiOperation(name = "分页列表")
    @PostMapping(path = "/rbac-resource-page")
    public Result<List<RbacResourcePageVO>> page(@Valid @RequestBody RbacResourceQueryDTO dto) {
        return WebResult.success(this.rbacResourceService.page(dto));
    }

    @ApiOperation(name = "单个新增")
    @PostMapping(path = "/rbac-resource-create")
    public Result<Boolean> create(@Valid @RequestBody RbacResourceCreateDTO dto) {
        return WebResult.success(this.rbacResourceService.create(dto));
    }

    @ApiOperation(name = "单个编辑")
    @PutMapping(path = "/rbac-resource-update")
    public Result<Boolean> update(@Valid @RequestBody RbacResourceUpdateDTO dto) {
        return WebResult.success(this.rbacResourceService.update(dto));
    }

    @ApiOperation(name = "基础信息")
    @GetMapping(path = "/rbac-resource-show")
    public Result<RbacResourceBaseVO> show(@RequestParam("id") Long id) {
        return WebResult.success(this.rbacResourceService.show(id));
    }

    @ApiOperation(name = "详细信息")
    @GetMapping(path = "/rbac-resource-detail")
    public Result<RbacResourceDetailVO> detail(@RequestParam("id") Long id) {
        return WebResult.success(this.rbacResourceService.detail(id));
    }

    @ApiOperation(name = "批量删除")
    @DeleteMapping(path = "/rbac-resource-remove")
    public Result<Boolean> remove(@Valid @RequestBody IdsDTO dto) {
        return WebResult.success(this.rbacResourceService.remove(dto));
    }

}
