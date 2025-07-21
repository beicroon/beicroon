package com.beicroon.module.banner.app.controller.admin;

import com.beicroon.construct.base.entity.IdsDTO;
import com.beicroon.construct.base.entity.TabVO;
import com.beicroon.construct.base.entity.Result;
import com.beicroon.construct.base.entity.WebResult;
import com.beicroon.module.banner.app.service.IBannerBannerService;
import com.beicroon.module.banner.entity.bannerbanner.dto.BannerBannerCreateDTO;
import com.beicroon.module.banner.entity.bannerbanner.dto.BannerBannerQueryDTO;
import com.beicroon.module.banner.entity.bannerbanner.dto.BannerBannerUpdateDTO;
import com.beicroon.module.banner.entity.bannerbanner.vo.BannerBannerBaseVO;
import com.beicroon.module.banner.entity.bannerbanner.vo.BannerBannerDetailVO;
import com.beicroon.module.banner.entity.bannerbanner.vo.BannerBannerPageVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@Tag(name = "管理端/轮播图相关")
@RequestMapping(path = "/api/admin")
public class AdminBannerBannerController {

    @Resource
    private IBannerBannerService bannerBannerService;

    @Operation(summary = "分页统计")
    @PostMapping(path = "/banner-banner-tab")
    public Result<List<TabVO>> tab(@Valid @RequestBody BannerBannerQueryDTO dto) {
        return WebResult.success(this.bannerBannerService.tab(dto));
    }

    @Operation(summary = "全量列表")
    @PostMapping(path = "/banner-banner-list")
    public Result<List<BannerBannerBaseVO>> list(@Valid @RequestBody BannerBannerQueryDTO dto) {
        return WebResult.success(this.bannerBannerService.list(dto));
    }

    @Operation(summary = "分页列表")
    @PostMapping(path = "/banner-banner-page")
    public Result<List<BannerBannerPageVO>> page(@Valid @RequestBody BannerBannerQueryDTO dto) {
        return WebResult.success(this.bannerBannerService.page(dto));
    }

    @Operation(summary = "单个新增")
    @PostMapping(path = "/banner-banner-create")
    public Result<Boolean> create(@Valid @RequestBody BannerBannerCreateDTO dto) {
        return WebResult.success(this.bannerBannerService.create(dto));
    }

    @Operation(summary = "单个编辑")
    @PutMapping(path = "/banner-banner-update")
    public Result<Boolean> update(@Valid @RequestBody BannerBannerUpdateDTO dto) {
        return WebResult.success(this.bannerBannerService.update(dto));
    }

    @Operation(summary = "基础信息")
    @GetMapping(path = "/banner-banner-show")
    public Result<BannerBannerBaseVO> show(@RequestParam("id") Long id) {
        return WebResult.success(this.bannerBannerService.show(id));
    }

    @Operation(summary = "详细信息")
    @GetMapping(path = "/banner-banner-detail")
    public Result<BannerBannerDetailVO> detail(@RequestParam("id") Long id) {
        return WebResult.success(this.bannerBannerService.detail(id));
    }

    @Operation(summary = "批量删除")
    @DeleteMapping(path = "/banner-banner-remove")
    public Result<Boolean> remove(@Valid @RequestBody IdsDTO dto) {
        return WebResult.success(this.bannerBannerService.remove(dto));
    }

}
