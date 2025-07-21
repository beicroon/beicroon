package com.beicroon.module.file.entity.filefile.dto;

import com.beicroon.construct.base.annotation.NeedValue;
import io.swagger.v3.oas.annotations.media.Schema;
import com.beicroon.construct.base.entity.IdDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class FileFileUpdateDTO extends IdDTO {

    @NeedValue(message = "文件地址不能为空")
    @Schema(name = "fileUrl", description = "文件地址")
    private String fileUrl;

    @Schema(name = "fileType", description = "文件类型")
    private String fileType;

    @Schema(name = "fileName", description = "文件名称")
    private String fileName;

    @Schema(name = "fileExt", description = "文件后缀")
    private String fileExt;

    @Schema(name = "fileSize", description = "文件大小")
    private Long fileSize;

}
