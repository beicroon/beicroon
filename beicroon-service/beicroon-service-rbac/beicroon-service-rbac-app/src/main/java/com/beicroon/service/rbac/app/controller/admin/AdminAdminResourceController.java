package com.beicroon.service.rbac.app.controller.admin;

import com.beicroon.construct.annotation.ApiOperation;
import com.beicroon.construct.annotation.ApiTag;
import com.beicroon.construct.base.entity.IdsDTO;
import com.beicroon.construct.base.entity.Result;
import com.beicroon.construct.base.entity.TabVO;
import com.beicroon.construct.base.entity.WebResult;
import com.beicroon.service.rbac.app.service.IAdminResourceService;
import com.beicroon.service.rbac.entity.adminresource.dto.AdminResourceCreateDTO;
import com.beicroon.service.rbac.entity.adminresource.dto.AdminResourceQueryDTO;
import com.beicroon.service.rbac.entity.adminresource.dto.AdminResourceUpdateDTO;
import com.beicroon.service.rbac.entity.adminresource.vo.AdminResourceBaseVO;
import com.beicroon.service.rbac.entity.adminresource.vo.AdminResourceDetailVO;
import com.beicroon.service.rbac.entity.adminresource.vo.AdminResourcePageVO;
import com.beicroon.service.rbac.entity.adminresource.vo.AdminResourceTreeVO;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@ApiTag(name = "资源相关")
@RequestMapping(path = "/api/admin/admin")
public class AdminAdminResourceController {

    @Resource
    private IAdminResourceService adminResourceService;

    @ApiOperation(name = "分页统计")
    @PostMapping(path = "/admin-resource-tab")
    public Result<List<TabVO>> tab(@Valid @RequestBody AdminResourceQueryDTO dto) {
        return WebResult.success(this.adminResourceService.tab(dto));
    }

    @ApiOperation(name = "全量列表")
    @PostMapping(path = "/admin-resource-list")
    public Result<List<AdminResourceBaseVO>> list(@Valid @RequestBody AdminResourceQueryDTO dto) {
        return WebResult.success(this.adminResourceService.list(dto));
    }

    @ApiOperation(name = "树状列表")
    @PostMapping(path = "/admin-resource-tree")
    public Result<List<AdminResourceTreeVO>> tree(@Valid @RequestBody AdminResourceQueryDTO dto) {
        return WebResult.success(this.adminResourceService.tree(dto));
    }

    @ApiOperation(name = "分页列表")
    @PostMapping(path = "/admin-resource-page")
    public Result<List<AdminResourcePageVO>> page(@Valid @RequestBody AdminResourceQueryDTO dto) {
        return WebResult.success(this.adminResourceService.page(dto));
    }

    @ApiOperation(name = "单个新增")
    @PostMapping(path = "/admin-resource-create")
    public Result<Boolean> create(@Valid @RequestBody AdminResourceCreateDTO dto) {
        return WebResult.success(this.adminResourceService.create(dto));
    }

    @ApiOperation(name = "单个编辑")
    @PutMapping(path = "/admin-resource-update")
    public Result<Boolean> update(@Valid @RequestBody AdminResourceUpdateDTO dto) {
        return WebResult.success(this.adminResourceService.update(dto));
    }

    @ApiOperation(name = "基础信息")
    @GetMapping(path = "/admin-resource-show")
    public Result<AdminResourceBaseVO> show(@RequestParam("id") Long id) {
        return WebResult.success(this.adminResourceService.show(id));
    }

    @ApiOperation(name = "详细信息")
    @GetMapping(path = "/admin-resource-detail")
    public Result<AdminResourceDetailVO> detail(@RequestParam("id") Long id) {
        return WebResult.success(this.adminResourceService.detail(id));
    }

    @ApiOperation(name = "批量删除")
    @DeleteMapping(path = "/admin-resource-remove")
    public Result<Boolean> remove(@Valid @RequestBody IdsDTO dto) {
        return WebResult.success(this.adminResourceService.remove(dto));
    }

}
