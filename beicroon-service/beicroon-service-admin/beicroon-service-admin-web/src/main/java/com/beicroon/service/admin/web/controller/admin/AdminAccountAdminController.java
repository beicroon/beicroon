            package com.beicroon.service.admin.web.controller.admin;

            import com.beicroon.construct.annotation.ApiOperation;
            import com.beicroon.construct.annotation.ApiTag;
            import com.beicroon.construct.entity.IdsDTO;
            import com.beicroon.construct.entity.Result;
            import com.beicroon.construct.entity.WebResult;
            import com.beicroon.service.admin.entity.account.admin.dto.AccountAdminCreateDTO;
            import com.beicroon.service.admin.entity.account.admin.dto.AccountAdminQueryDTO;
            import com.beicroon.service.admin.entity.account.admin.dto.AccountAdminUpdateDTO;
            import com.beicroon.service.admin.entity.account.admin.vo.AccountAdminBaseVO;
            import com.beicroon.service.admin.entity.account.admin.vo.AccountAdminDetailVO;
            import com.beicroon.service.admin.entity.account.admin.vo.AccountAdminPageVO;
            import com.beicroon.service.admin.web.service.IAccountAdminService;
            import jakarta.annotation.Resource;
            import org.springframework.validation.annotation.Validated;
            import org.springframework.web.bind.annotation.*;

            import java.util.List;

            @Validated
            @RestController
            @ApiTag("后台账号相关")
            @RequestMapping("/api/admin/admin")
            public class AdminAccountAdminController {

                @Resource
                private IAccountAdminService accountAdminService;

                @ApiOperation("基础信息")
                @GetMapping("/account-admin-show")
                public Result<AccountAdminBaseVO> show(@RequestParam("id") Long id) {
                    return WebResult.success(this.accountAdminService.show(id));
                }

                @ApiOperation("详细信息")
                @GetMapping("/account-admin-detail")
                public Result<AccountAdminDetailVO> detail(@RequestParam("id") Long id) {
                    return WebResult.success(this.accountAdminService.detail(id));
                }

                @ApiOperation("全量列表")
                @PostMapping("/account-admin-list")
                public Result<List<AccountAdminBaseVO>> list(@RequestBody AccountAdminQueryDTO dto) {
                    return WebResult.success(this.accountAdminService.list(dto));
                }

                @ApiOperation("分页列表")
                @PostMapping("/account-admin-page")
                public Result<List<AccountAdminPageVO>> page(@RequestBody AccountAdminQueryDTO dto) {
                    return WebResult.success(this.accountAdminService.page(dto));
                }

                @ApiOperation("单个新增")
                @PostMapping("/account-admin-create")
                public Result<Boolean> create(@RequestBody AccountAdminCreateDTO dto) {
                    return WebResult.success(this.accountAdminService.create(dto));
                }

                @ApiOperation("单个修改")
                @PutMapping("/account-admin-update")
                public Result<Boolean> update(@RequestBody AccountAdminUpdateDTO dto) {
                    return WebResult.success(this.accountAdminService.update(dto));
                }

//                @ApiOperation("批量启用")
//                @PutMapping("/account-admin-enable")
//                public Result<Boolean> enable(@RequestBody IdsDTO dto) {
//                    return WebResult.success(this.accountAdminService.enable(dto));
//                }

//                @ApiOperation("批量禁用")
//                @PutMapping("/account-admin-disable")
//                public Result<Boolean> disable(@RequestBody IdsDTO dto) {
//                    return WebResult.success(this.accountAdminService.disable(dto));
//                }

                @ApiOperation("批量删除")
                @DeleteMapping("/account-admin-remove")
                public Result<Boolean> remove(@RequestBody IdsDTO dto) {
                    return WebResult.success(this.accountAdminService.remove(dto));
                }

            }
