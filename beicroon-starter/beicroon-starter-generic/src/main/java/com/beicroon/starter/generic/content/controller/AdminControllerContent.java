package com.beicroon.starter.generic.content.controller;

import com.beicroon.starter.generic.entity.Table;
import com.beicroon.starter.generic.manager.PackageManager;

public class AdminControllerContent {

    private static final String CONTENT = """
            package {{package}}.controller.admin;

            import com.beicroon.construct.annotation.ApiOperation;
            import com.beicroon.construct.annotation.ApiTag;
            import com.beicroon.construct.entity.IdsDTO;
            import com.beicroon.construct.entity.Result;
            import com.beicroon.construct.entity.WebResult;
            import {{modulePackage}}.dto.{{filename}}CreateDTO;
            import {{modulePackage}}.dto.{{filename}}QueryDTO;
            import {{modulePackage}}.dto.{{filename}}UpdateDTO;
            import {{modulePackage}}.vo.{{filename}}DetailVO;
            import {{modulePackage}}.vo.{{filename}}BaseVO;
            import {{modulePackage}}.vo.{{filename}}PageVO;
            import {{package}}.service.I{{filename}}Service;
            import jakarta.annotation.Resource;
            import jakarta.validation.Valid;
            import org.springframework.validation.annotation.Validated;
            import org.springframework.web.bind.annotation.*;

            import java.util.List;

            @Validated
            @RestController
            @ApiTag(name = "{{comment}}相关")
            @RequestMapping(path = "/api/{{modulePrefix}}/admin")
            public class Admin{{filename}}Controller {

                @Resource
                private I{{filename}}Service {{variableName}}Service;

                @ApiOperation(name = "基础信息")
                @GetMapping(path = "/{{urlName}}-show")
                public Result<{{filename}}BaseVO> show(@RequestParam("id") Long id) {
                    return WebResult.success(this.{{variableName}}Service.show(id));
                }

                @ApiOperation(name = "详细信息")
                @GetMapping(path = "/{{urlName}}-detail")
                public Result<{{filename}}DetailVO> detail(@RequestParam("id") Long id) {
                    return WebResult.success(this.{{variableName}}Service.detail(id));
                }

                @ApiOperation(name = "分页列表")
                @PostMapping(path = "/{{urlName}}-page")
                public Result<List<{{filename}}PageVO>> page(@Valid @RequestBody {{filename}}QueryDTO dto) {
                    return WebResult.success(this.{{variableName}}Service.page(dto));
                }

                @ApiOperation(name = "全量列表")
                @PostMapping(path = "/{{urlName}}-list")
                public Result<List<{{filename}}BaseVO>> list(@Valid @RequestBody {{filename}}QueryDTO dto) {
                    return WebResult.success(this.{{variableName}}Service.list(dto));
                }

                @ApiOperation(name = "单个新增")
                @PostMapping(path = "/{{urlName}}-create")
                public Result<Boolean> create(@Valid @RequestBody {{filename}}CreateDTO dto) {
                    return WebResult.success(this.{{variableName}}Service.create(dto));
                }

                @ApiOperation(name = "单个修改")
                @PutMapping(path = "/{{urlName}}-update")
                public Result<Boolean> update(@Valid @RequestBody {{filename}}UpdateDTO dto) {
                    return WebResult.success(this.{{variableName}}Service.update(dto));
                }

                @ApiOperation(name = "批量删除")
                @DeleteMapping(path = "/{{urlName}}-remove")
                public Result<Boolean> remove(@Valid @RequestBody IdsDTO dto) {
                    return WebResult.success(this.{{variableName}}Service.remove(dto));
                }

            }
            """;

    public static String getContent(PackageManager packageManager, Table table) {
        return CONTENT
                .replace("{{package}}", packageManager.getWebPackage())
                .replace("{{modulePackage}}", packageManager.getEntityPackage() + "." + table.getPackage())
                .replace("{{comment}}", table.getComment())
                .replace("{{modulePrefix}}", table.getPrefix())
                .replace("{{variableName}}", table.getVariableName())
                .replace("{{filename}}", table.getFilename())
                .replace("{{urlName}}", table.getUrlName());
    }

}
