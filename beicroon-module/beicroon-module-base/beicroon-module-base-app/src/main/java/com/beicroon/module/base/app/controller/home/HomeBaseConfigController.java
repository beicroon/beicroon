package com.beicroon.module.base.app.controller.home;

import com.beicroon.construct.base.entity.Result;
import com.beicroon.construct.base.entity.WebResult;
import com.beicroon.module.base.app.service.IBaseConfigService;
import com.beicroon.module.base.entity.baseconfig.vo.BaseConfigDetailVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@Tag(name = "用户端/配置相关")
@RequestMapping(path = "/api/home")
public class HomeBaseConfigController {

    @Resource
    private IBaseConfigService baseConfigService;

    @Operation(summary = "详细信息(key获取)")
    @GetMapping(path = "/base-config-detail-by-key")
    public Result<BaseConfigDetailVO> detailByKey(@RequestParam("key") String key) {
        return WebResult.success(this.baseConfigService.detailByKey(key));
    }

}
