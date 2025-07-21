package com.beicroon.module.file.dao.bo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class FileInfoBO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(name = "fileType", description = "文件类型")
    private String fileType;

    @Schema(name = "fileName", description = "文件名称")
    private String fileName;

    @Schema(name = "fileExt", description = "文件后缀")
    private String fileExt;

    @Schema(name = "fileSize", description = "文件大小")
    private Long fileSize;

}
