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
            import org.springframework.validation.annotation.Validated;
            import org.springframework.web.bind.annotation.*;

            import java.util.List;

            @Validated
            @RestController
            @ApiTag("{{comment}}相关")
            @RequestMapping("/api/admin/admin")
            public class Admin{{filename}}Controller {

                @Resource
                private I{{filename}}Service {{variableName}}Service;

                @ApiOperation("基础信息")
                @GetMapping("/{{urlName}}-show")
                public Result<{{filename}}BaseVO> show(@RequestParam("id") Long id) {
                    return WebResult.success(this.{{variableName}}Service.show(id));
                }

                @ApiOperation("详细信息")
                @GetMapping("/{{urlName}}-detail")
                public Result<{{filename}}DetailVO> detail(@RequestParam("id") Long id) {
                    return WebResult.success(this.{{variableName}}Service.detail(id));
                }

                @ApiOperation("全量列表")
                @PostMapping("/{{urlName}}-list")
                public Result<List<{{filename}}BaseVO>> list(@RequestBody {{filename}}QueryDTO dto) {
                    return WebResult.success(this.{{variableName}}Service.list(dto));
                }

                @ApiOperation("分页列表")
                @PostMapping("/{{urlName}}-page")
                public Result<List<{{filename}}PageVO>> page(@RequestBody {{filename}}QueryDTO dto) {
                    return WebResult.success(this.{{variableName}}Service.page(dto));
                }

                @ApiOperation("单个新增")
                @PostMapping("/{{urlName}}-create")
                public Result<Boolean> create(@RequestBody {{filename}}CreateDTO dto) {
                    return WebResult.success(this.{{variableName}}Service.create(dto));
                }

                @ApiOperation("单个修改")
                @PutMapping("/{{urlName}}-update")
                public Result<Boolean> update(@RequestBody {{filename}}UpdateDTO dto) {
                    return WebResult.success(this.{{variableName}}Service.update(dto));
                }

//                @ApiOperation("批量启用")
//                @PutMapping("/{{urlName}}-enable")
//                public Result<Boolean> enable(@RequestBody IdsDTO dto) {
//                    return WebResult.success(this.{{variableName}}Service.enable(dto));
//                }

//                @ApiOperation("批量禁用")
//                @PutMapping("/{{urlName}}-disable")
//                public Result<Boolean> disable(@RequestBody IdsDTO dto) {
//                    return WebResult.success(this.{{variableName}}Service.disable(dto));
//                }

                @ApiOperation("批量删除")
                @DeleteMapping("/{{urlName}}-remove")
                public Result<Boolean> remove(@RequestBody IdsDTO dto) {
                    return WebResult.success(this.{{variableName}}Service.remove(dto));
                }

            }
            """;

    public static String getContent(PackageManager packageManager, Table table) {
        return CONTENT
                .replaceAll("\\{\\{package}}", packageManager.getWebPackage())
                .replaceAll("\\{\\{modulePackage}}", packageManager.getEntityPackage() + "." + table.getPackage())
                .replaceAll("\\{\\{comment}}", table.getComment())
                .replaceAll("\\{\\{variableName}}", table.getVariableName())
                .replaceAll("\\{\\{filename}}", table.getFilename())
                .replaceAll("\\{\\{urlName}}", table.getUrlName());
    }

}
