package com.beicroon.service.file.app.controller.admin;

import com.beicroon.construct.annotation.ApiOperation;
import com.beicroon.construct.annotation.ApiTag;
import com.beicroon.construct.base.entity.IdsDTO;
import com.beicroon.construct.base.entity.Result;
import com.beicroon.construct.base.entity.TabVO;
import com.beicroon.construct.base.entity.WebResult;
import com.beicroon.service.file.app.service.IFileFileService;
import com.beicroon.service.file.entity.filefile.dto.FileFileCreateDTO;
import com.beicroon.service.file.entity.filefile.dto.FileFileQueryDTO;
import com.beicroon.service.file.entity.filefile.dto.FileFileUpdateDTO;
import com.beicroon.service.file.entity.filefile.vo.FileFileBaseVO;
import com.beicroon.service.file.entity.filefile.vo.FileFileDetailVO;
import com.beicroon.service.file.entity.filefile.vo.FileFilePageVO;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@ApiTag(name = "文件相关")
@RequestMapping(path = "/api/admin")
public class AdminFileFileController {

    @Resource
    private IFileFileService fileFileService;

    @ApiOperation(name = "分页统计")
    @PostMapping(path = "/file-file-tab")
    public Result<List<TabVO>> tab(@Valid @RequestBody FileFileQueryDTO dto) {
        return WebResult.success(this.fileFileService.tab(dto));
    }

    @ApiOperation(name = "全量列表")
    @PostMapping(path = "/file-file-list")
    public Result<List<FileFileBaseVO>> list(@Valid @RequestBody FileFileQueryDTO dto) {
        return WebResult.success(this.fileFileService.list(dto));
    }

    @ApiOperation(name = "分页列表")
    @PostMapping(path = "/file-file-page")
    public Result<List<FileFilePageVO>> page(@Valid @RequestBody FileFileQueryDTO dto) {
        return WebResult.success(this.fileFileService.page(dto));
    }

    @ApiOperation(name = "单个新增")
    @PostMapping(path = "/file-file-create")
    public Result<Boolean> create(@Valid @RequestBody FileFileCreateDTO dto) {
        return WebResult.success(this.fileFileService.create(dto));
    }

    @ApiOperation(name = "单个编辑")
    @PutMapping(path = "/file-file-update")
    public Result<Boolean> update(@Valid @RequestBody FileFileUpdateDTO dto) {
        return WebResult.success(this.fileFileService.update(dto));
    }

    @ApiOperation(name = "基础信息")
    @GetMapping(path = "/file-file-show")
    public Result<FileFileBaseVO> show(@RequestParam("id") Long id) {
        return WebResult.success(this.fileFileService.show(id));
    }

    @ApiOperation(name = "详细信息")
    @GetMapping(path = "/file-file-detail")
    public Result<FileFileDetailVO> detail(@RequestParam("id") Long id) {
        return WebResult.success(this.fileFileService.detail(id));
    }

    @ApiOperation(name = "批量删除")
    @DeleteMapping(path = "/file-file-remove")
    public Result<Boolean> remove(@Valid @RequestBody IdsDTO dto) {
        return WebResult.success(this.fileFileService.remove(dto));
    }

}
