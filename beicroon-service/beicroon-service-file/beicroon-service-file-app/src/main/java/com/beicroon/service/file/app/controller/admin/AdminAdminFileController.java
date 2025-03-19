package com.beicroon.service.file.app.controller.admin;

import com.beicroon.construct.annotation.ApiOperation;
import com.beicroon.construct.annotation.ApiTag;
import com.beicroon.construct.base.entity.IdsDTO;
import com.beicroon.construct.base.entity.Result;
import com.beicroon.construct.base.entity.WebResult;
import com.beicroon.service.file.app.service.IAdminFileService;
import com.beicroon.service.file.entity.adminfile.vo.AdminFileBaseVO;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Validated
@RestController
@ApiTag(name = "文件相关")
@RequestMapping(path = "/api/admin/admin")
public class AdminAdminFileController {

    @Resource
    private IAdminFileService adminFileService;

    @ApiOperation(name = "上传文件")
    @PostMapping(path = "/admin-file-upload")
    public Result<AdminFileBaseVO> upload(@RequestParam("file") MultipartFile file) {
        return WebResult.success(this.adminFileService.upload(file));
    }

    @ApiOperation(name = "上传图片")
    @PostMapping(path = "/admin-file-upload-image")
    public Result<AdminFileBaseVO> uploadImage(@RequestParam("image") MultipartFile image) {
        return WebResult.success(this.adminFileService.uploadImage(image));
    }

    @ApiOperation(name = "批量删除")
    @DeleteMapping(path = "/admin-file-remove")
    public Result<Boolean> remove(@Valid @RequestBody IdsDTO dto) {
        return WebResult.success(this.adminFileService.remove(dto));
    }

}
