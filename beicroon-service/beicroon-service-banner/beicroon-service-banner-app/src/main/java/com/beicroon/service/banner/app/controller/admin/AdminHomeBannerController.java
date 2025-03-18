package com.beicroon.service.banner.app.controller.admin;

import com.beicroon.construct.annotation.ApiOperation;
import com.beicroon.construct.annotation.ApiTag;
import com.beicroon.construct.base.entity.IdsDTO;
import com.beicroon.construct.base.entity.Result;
import com.beicroon.construct.base.entity.TabVO;
import com.beicroon.construct.base.entity.WebResult;
import com.beicroon.service.banner.app.service.IHomeBannerService;
import com.beicroon.service.banner.entity.homebanner.dto.HomeBannerCreateDTO;
import com.beicroon.service.banner.entity.homebanner.dto.HomeBannerQueryDTO;
import com.beicroon.service.banner.entity.homebanner.dto.HomeBannerUpdateDTO;
import com.beicroon.service.banner.entity.homebanner.vo.HomeBannerBaseVO;
import com.beicroon.service.banner.entity.homebanner.vo.HomeBannerDetailVO;
import com.beicroon.service.banner.entity.homebanner.vo.HomeBannerPageVO;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@ApiTag(name = "轮播图相关")
@RequestMapping(path = "/api/admin/admin")
public class AdminHomeBannerController {

    @Resource
    private IHomeBannerService homeBannerService;

    @ApiOperation(name = "分页统计")
    @PostMapping(path = "/home-banner-tab")
    public Result<List<TabVO>> tab(@Valid @RequestBody HomeBannerQueryDTO dto) {
        return WebResult.success(this.homeBannerService.tab(dto));
    }

    @ApiOperation(name = "全量列表")
    @PostMapping(path = "/home-banner-list")
    public Result<List<HomeBannerBaseVO>> list(@Valid @RequestBody HomeBannerQueryDTO dto) {
        return WebResult.success(this.homeBannerService.list(dto));
    }

    @ApiOperation(name = "分页列表")
    @PostMapping(path = "/home-banner-page")
    public Result<List<HomeBannerPageVO>> page(@Valid @RequestBody HomeBannerQueryDTO dto) {
        return WebResult.success(this.homeBannerService.page(dto));
    }

    @ApiOperation(name = "单个新增")
    @PostMapping(path = "/home-banner-create")
    public Result<Boolean> create(@Valid @RequestBody HomeBannerCreateDTO dto) {
        return WebResult.success(this.homeBannerService.create(dto));
    }

    @ApiOperation(name = "单个编辑")
    @PutMapping(path = "/home-banner-update")
    public Result<Boolean> update(@Valid @RequestBody HomeBannerUpdateDTO dto) {
        return WebResult.success(this.homeBannerService.update(dto));
    }

    @ApiOperation(name = "基础信息")
    @GetMapping(path = "/home-banner-show")
    public Result<HomeBannerBaseVO> show(@RequestParam("id") Long id) {
        return WebResult.success(this.homeBannerService.show(id));
    }

    @ApiOperation(name = "详细信息")
    @GetMapping(path = "/home-banner-detail")
    public Result<HomeBannerDetailVO> detail(@RequestParam("id") Long id) {
        return WebResult.success(this.homeBannerService.detail(id));
    }

    @ApiOperation(name = "批量删除")
    @DeleteMapping(path = "/home-banner-remove")
    public Result<Boolean> remove(@Valid @RequestBody IdsDTO dto) {
        return WebResult.success(this.homeBannerService.remove(dto));
    }

}
