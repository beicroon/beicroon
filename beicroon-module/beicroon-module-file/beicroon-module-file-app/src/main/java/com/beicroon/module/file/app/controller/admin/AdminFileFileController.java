package com.beicroon.module.file.app.controller.admin;

import com.beicroon.construct.base.entity.IdsDTO;
import com.beicroon.construct.base.entity.TabVO;
import com.beicroon.construct.base.entity.Result;
import com.beicroon.construct.base.entity.WebResult;
import com.beicroon.module.file.app.service.IFileFileService;
import com.beicroon.module.file.entity.filefile.dto.FileFileCreateDTO;
import com.beicroon.module.file.entity.filefile.dto.FileFileQueryDTO;
import com.beicroon.module.file.entity.filefile.dto.FileFileUpdateDTO;
import com.beicroon.module.file.entity.filefile.vo.FileFileBaseVO;
import com.beicroon.module.file.entity.filefile.vo.FileFileDetailVO;
import com.beicroon.module.file.entity.filefile.vo.FileFilePageVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Validated
@RestController
@Tag(name = "管理端/文件相关")
@RequestMapping(path = "/api/admin")
public class AdminFileFileController {

    @Resource
    private IFileFileService fileFileService;

    @Operation(summary = "分页统计")
    @PostMapping(path = "/file-file-tab")
    public Result<List<TabVO>> tab(@Valid @RequestBody FileFileQueryDTO dto) {
        return WebResult.success(this.fileFileService.tab(dto));
    }

    @Operation(summary = "全量列表")
    @PostMapping(path = "/file-file-list")
    public Result<List<FileFileBaseVO>> list(@Valid @RequestBody FileFileQueryDTO dto) {
        return WebResult.success(this.fileFileService.list(dto));
    }

    @Operation(summary = "分页列表")
    @PostMapping(path = "/file-file-page")
    public Result<List<FileFilePageVO>> page(@Valid @RequestBody FileFileQueryDTO dto) {
        return WebResult.success(this.fileFileService.page(dto));
    }

    @Operation(summary = "单个新增")
    @PostMapping(path = "/file-file-create")
    public Result<Boolean> create(@Valid @RequestBody FileFileCreateDTO dto) {
        return WebResult.success(this.fileFileService.create(dto));
    }

    @Operation(summary = "单个编辑")
    @PutMapping(path = "/file-file-update")
    public Result<Boolean> update(@Valid @RequestBody FileFileUpdateDTO dto) {
        return WebResult.success(this.fileFileService.update(dto));
    }

    @Operation(summary = "基础信息")
    @GetMapping(path = "/file-file-show")
    public Result<FileFileBaseVO> show(@RequestParam("id") Long id) {
        return WebResult.success(this.fileFileService.show(id));
    }

    @Operation(summary = "详细信息")
    @GetMapping(path = "/file-file-detail")
    public Result<FileFileDetailVO> detail(@RequestParam("id") Long id) {
        return WebResult.success(this.fileFileService.detail(id));
    }

    @Operation(summary = "批量删除")
    @DeleteMapping(path = "/file-file-remove")
    public Result<Boolean> remove(@Valid @RequestBody IdsDTO dto) {
        return WebResult.success(this.fileFileService.remove(dto));
    }

    @Operation(summary = "上传文件")
    @PostMapping(path = "/file-file-upload")
    public Result<FileFileBaseVO> upload(@RequestParam("file") MultipartFile file) {
        return WebResult.success(this.fileFileService.uploadFile(file));
    }

    @Operation(summary = "上传图片")
    @PostMapping(path = "/file-file-image-upload")
    public Result<FileFileBaseVO> uploadImage(@RequestParam("image") MultipartFile image) {
        return WebResult.success(this.fileFileService.uploadImage(image));
    }

}
