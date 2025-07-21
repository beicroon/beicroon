package com.beicroon.module.file.dao.model;

import io.swagger.v3.oas.annotations.media.Schema;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.beicroon.construct.database.model.GenericModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "`file_file`")
public class FileFileModel extends GenericModel {

    @TableField(value = "`file_url`")
    @Schema(name = "fileUrl", description = "文件地址")
    private String fileUrl;

    @TableField(value = "`file_type`")
    @Schema(name = "fileType", description = "文件类型")
    private String fileType;

    @TableField(value = "`file_name`")
    @Schema(name = "fileName", description = "文件名称")
    private String fileName;

    @TableField(value = "`file_ext`")
    @Schema(name = "fileExt", description = "文件后缀")
    private String fileExt;

    @TableField(value = "`file_size`")
    @Schema(name = "fileSize", description = "文件大小")
    private Long fileSize;

}
