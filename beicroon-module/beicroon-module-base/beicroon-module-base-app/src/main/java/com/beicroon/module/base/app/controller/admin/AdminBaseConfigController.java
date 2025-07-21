package com.beicroon.module.base.app.controller.admin;

import com.beicroon.construct.base.entity.IdsDTO;
import com.beicroon.construct.base.entity.TabVO;
import com.beicroon.construct.base.entity.Result;
import com.beicroon.construct.base.entity.WebResult;
import com.beicroon.module.base.app.service.IBaseConfigService;
import com.beicroon.module.base.entity.baseconfig.dto.BaseConfigCreateDTO;
import com.beicroon.module.base.entity.baseconfig.dto.BaseConfigQueryDTO;
import com.beicroon.module.base.entity.baseconfig.dto.BaseConfigUpdateDTO;
import com.beicroon.module.base.entity.baseconfig.vo.BaseConfigBaseVO;
import com.beicroon.module.base.entity.baseconfig.vo.BaseConfigDetailVO;
import com.beicroon.module.base.entity.baseconfig.vo.BaseConfigPageVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@Tag(name = "管理端/配置相关")
@RequestMapping(path = "/api/admin")
public class AdminBaseConfigController {

    @Resource
    private IBaseConfigService baseConfigService;

    @Operation(summary = "分页统计")
    @PostMapping(path = "/base-config-tab")
    public Result<List<TabVO>> tab(@Valid @RequestBody BaseConfigQueryDTO dto) {
        return WebResult.success(this.baseConfigService.tab(dto));
    }

    @Operation(summary = "全量列表")
    @PostMapping(path = "/base-config-list")
    public Result<List<BaseConfigBaseVO>> list(@Valid @RequestBody BaseConfigQueryDTO dto) {
        return WebResult.success(this.baseConfigService.list(dto));
    }

    @Operation(summary = "分页列表")
    @PostMapping(path = "/base-config-page")
    public Result<List<BaseConfigPageVO>> page(@Valid @RequestBody BaseConfigQueryDTO dto) {
        return WebResult.success(this.baseConfigService.page(dto));
    }

    @Operation(summary = "单个新增")
    @PostMapping(path = "/base-config-create")
    public Result<Boolean> create(@Valid @RequestBody BaseConfigCreateDTO dto) {
        return WebResult.success(this.baseConfigService.create(dto));
    }

    @Operation(summary = "单个编辑")
    @PutMapping(path = "/base-config-update")
    public Result<Boolean> update(@Valid @RequestBody BaseConfigUpdateDTO dto) {
        return WebResult.success(this.baseConfigService.update(dto));
    }

    @Operation(summary = "基础信息")
    @GetMapping(path = "/base-config-show")
    public Result<BaseConfigBaseVO> show(@RequestParam("id") Long id) {
        return WebResult.success(this.baseConfigService.show(id));
    }

    @Operation(summary = "详细信息")
    @GetMapping(path = "/base-config-detail")
    public Result<BaseConfigDetailVO> detail(@RequestParam("id") Long id) {
        return WebResult.success(this.baseConfigService.detail(id));
    }

    @Operation(summary = "详细信息(key获取)")
    @GetMapping(path = "/base-config-detail-by-key")
    public Result<BaseConfigDetailVO> detailByKey(@RequestParam("key") String key) {
        return WebResult.success(this.baseConfigService.detailByKey(key));
    }

    @Operation(summary = "批量删除")
    @DeleteMapping(path = "/base-config-remove")
    public Result<Boolean> remove(@Valid @RequestBody IdsDTO dto) {
        return WebResult.success(this.baseConfigService.remove(dto));
    }

}
