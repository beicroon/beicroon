package com.beicroon.module.banner.app.controller.home;

import com.beicroon.construct.base.entity.Result;
import com.beicroon.construct.base.entity.WebResult;
import com.beicroon.module.banner.app.service.IBannerBannerService;
import com.beicroon.module.banner.entity.bannerbanner.dto.BannerBannerQueryDTO;
import com.beicroon.module.banner.entity.bannerbanner.vo.BannerBannerBaseVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@Tag(name = "用户端/轮播图相关")
@RequestMapping(path = "/api/home")
public class HomeBannerBannerController {

    @Resource
    private IBannerBannerService bannerBannerService;

    @Operation(summary = "生效列表")
    @PostMapping(path = "/banner-banner-effective-list")
    public Result<List<BannerBannerBaseVO>> listEffective(@Valid @RequestBody BannerBannerQueryDTO dto) {
        return WebResult.success(this.bannerBannerService.listEffective(dto));
    }

}
