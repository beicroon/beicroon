            package com.beicroon.service.admin.web.controller.admin;

            import com.beicroon.construct.annotation.ApiOperation;
            import com.beicroon.construct.annotation.ApiTag;
            import com.beicroon.construct.entity.IdsDTO;
            import com.beicroon.construct.entity.Result;
            import com.beicroon.construct.entity.WebResult;
            import com.beicroon.service.admin.entity.resource.menu.dto.ResourceMenuCreateDTO;
            import com.beicroon.service.admin.entity.resource.menu.dto.ResourceMenuQueryDTO;
            import com.beicroon.service.admin.entity.resource.menu.dto.ResourceMenuUpdateDTO;
            import com.beicroon.service.admin.entity.resource.menu.vo.ResourceMenuBaseVO;
            import com.beicroon.service.admin.entity.resource.menu.vo.ResourceMenuDetailVO;
            import com.beicroon.service.admin.entity.resource.menu.vo.ResourceMenuPageVO;
            import com.beicroon.service.admin.web.service.IResourceMenuService;
            import jakarta.annotation.Resource;
            import org.springframework.validation.annotation.Validated;
            import org.springframework.web.bind.annotation.*;

            import java.util.List;

            @Validated
            @RestController
            @ApiTag("资源菜单相关")
            @RequestMapping("/api/admin/admin")
            public class AdminResourceMenuController {

                @Resource
                private IResourceMenuService resourceMenuService;

                @ApiOperation("基础信息")
                @GetMapping("/resource-menu-show")
                public Result<ResourceMenuBaseVO> show(@RequestParam("id") Long id) {
                    return WebResult.success(this.resourceMenuService.show(id));
                }

                @ApiOperation("详细信息")
                @GetMapping("/resource-menu-detail")
                public Result<ResourceMenuDetailVO> detail(@RequestParam("id") Long id) {
                    return WebResult.success(this.resourceMenuService.detail(id));
                }

                @ApiOperation("全量列表")
                @PostMapping("/resource-menu-list")
                public Result<List<ResourceMenuBaseVO>> list(@RequestBody ResourceMenuQueryDTO dto) {
                    return WebResult.success(this.resourceMenuService.list(dto));
                }

                @ApiOperation("分页列表")
                @PostMapping("/resource-menu-page")
                public Result<List<ResourceMenuPageVO>> page(@RequestBody ResourceMenuQueryDTO dto) {
                    return WebResult.success(this.resourceMenuService.page(dto));
                }

                @ApiOperation("单个新增")
                @PostMapping("/resource-menu-create")
                public Result<Boolean> create(@RequestBody ResourceMenuCreateDTO dto) {
                    return WebResult.success(this.resourceMenuService.create(dto));
                }

                @ApiOperation("单个修改")
                @PutMapping("/resource-menu-update")
                public Result<Boolean> update(@RequestBody ResourceMenuUpdateDTO dto) {
                    return WebResult.success(this.resourceMenuService.update(dto));
                }

//                @ApiOperation("批量启用")
//                @PutMapping("/resource-menu-enable")
//                public Result<Boolean> enable(@RequestBody IdsDTO dto) {
//                    return WebResult.success(this.resourceMenuService.enable(dto));
//                }

//                @ApiOperation("批量禁用")
//                @PutMapping("/resource-menu-disable")
//                public Result<Boolean> disable(@RequestBody IdsDTO dto) {
//                    return WebResult.success(this.resourceMenuService.disable(dto));
//                }

                @ApiOperation("批量删除")
                @DeleteMapping("/resource-menu-remove")
                public Result<Boolean> remove(@RequestBody IdsDTO dto) {
                    return WebResult.success(this.resourceMenuService.remove(dto));
                }

            }
