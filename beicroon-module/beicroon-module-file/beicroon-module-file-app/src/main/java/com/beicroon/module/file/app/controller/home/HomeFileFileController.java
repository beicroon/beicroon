package com.beicroon.module.file.app.controller.home;

import com.beicroon.construct.base.entity.Result;
import com.beicroon.construct.base.entity.WebResult;
import com.beicroon.module.file.app.service.IFileFileService;
import com.beicroon.module.file.entity.filefile.vo.FileFileBaseVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Validated
@RestController
@Tag(name = "用户端/文件相关")
@RequestMapping(path = "/api/home")
public class HomeFileFileController {

    @Resource
    private IFileFileService fileFileService;

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
