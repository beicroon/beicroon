package com.beicroon.starter.generic.content.app;

import com.beicroon.starter.generic.database.Table;
import com.beicroon.starter.generic.structure.GenericStructure;

public final class ControllerContent {
    
    private static final String CONTENT = """
            package {{modulePackage}}.app.controller.admin;

            import com.beicroon.construct.annotation.ApiOperation;
            import com.beicroon.construct.annotation.ApiTag;
            import com.beicroon.construct.base.entity.IdsDTO;
            import com.beicroon.construct.base.entity.Result;
            import com.beicroon.construct.base.entity.TabVO;
            import com.beicroon.construct.base.entity.WebResult;
            import {{modulePackage}}.app.service.I{{className}}Service;
            import {{modulePackage}}.entity.{{pathName}}.dto.{{className}}CreateDTO;
            import {{modulePackage}}.entity.{{pathName}}.dto.{{className}}QueryDTO;
            import {{modulePackage}}.entity.{{pathName}}.dto.{{className}}UpdateDTO;
            import {{modulePackage}}.entity.{{pathName}}.vo.{{className}}BaseVO;
            import {{modulePackage}}.entity.{{pathName}}.vo.{{className}}DetailVO;
            import {{modulePackage}}.entity.{{pathName}}.vo.{{className}}PageVO;
            import jakarta.annotation.Resource;
            import jakarta.validation.Valid;
            import org.springframework.validation.annotation.Validated;
            import org.springframework.web.bind.annotation.*;

            import java.util.List;

            @Validated
            @RestController
            @ApiTag(name = "{{comment}}相关")
            @RequestMapping(path = "/api/admin")
            public class Admin{{className}}Controller {

                @Resource
                private I{{className}}Service {{variableName}}Service;
            
                @ApiOperation(name = "分页统计")
                @PostMapping(path = "/{{urlName}}-tab")
                public Result<List<TabVO>> tab(@Valid @RequestBody {{className}}QueryDTO dto) {
                    return WebResult.success(this.{{variableName}}Service.tab(dto));
                }

                @ApiOperation(name = "全量列表")
                @PostMapping(path = "/{{urlName}}-list")
                public Result<List<{{className}}BaseVO>> list(@Valid @RequestBody {{className}}QueryDTO dto) {
                    return WebResult.success(this.{{variableName}}Service.list(dto));
                }

                @ApiOperation(name = "分页列表")
                @PostMapping(path = "/{{urlName}}-page")
                public Result<List<{{className}}PageVO>> page(@Valid @RequestBody {{className}}QueryDTO dto) {
                    return WebResult.success(this.{{variableName}}Service.page(dto));
                }

                @ApiOperation(name = "单个新增")
                @PostMapping(path = "/{{urlName}}-create")
                public Result<Boolean> create(@Valid @RequestBody {{className}}CreateDTO dto) {
                    return WebResult.success(this.{{variableName}}Service.create(dto));
                }

                @ApiOperation(name = "单个编辑")
                @PutMapping(path = "/{{urlName}}-update")
                public Result<Boolean> update(@Valid @RequestBody {{className}}UpdateDTO dto) {
                    return WebResult.success(this.{{variableName}}Service.update(dto));
                }

                @ApiOperation(name = "基础信息")
                @GetMapping(path = "/{{urlName}}-show")
                public Result<{{className}}BaseVO> show(@RequestParam("id") Long id) {
                    return WebResult.success(this.{{variableName}}Service.show(id));
                }

                @ApiOperation(name = "详细信息")
                @GetMapping(path = "/{{urlName}}-detail")
                public Result<{{className}}DetailVO> detail(@RequestParam("id") Long id) {
                    return WebResult.success(this.{{variableName}}Service.detail(id));
                }

                @ApiOperation(name = "批量删除")
                @DeleteMapping(path = "/{{urlName}}-remove")
                public Result<Boolean> remove(@Valid @RequestBody IdsDTO dto) {
                    return WebResult.success(this.{{variableName}}Service.remove(dto));
                }

            }
            """;
    
    private ControllerContent() {
    }

    public static String getContent(GenericStructure structure, Table table) {
        return CONTENT
                .replace("{{modulePackage}}", structure.getModulePackage())
                .replace("{{pathName}}", table.getPathName())
                .replace("{{className}}", table.getClassName())
                .replace("{{comment}}", table.getTableComment())
                .replace("{{urlName}}", table.getUrlName())
                .replace("{{variableName}}", table.getVariableName())
                ;
    }
    
}
