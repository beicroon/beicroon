package com.beicroon.service.file.dao.model;

import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.annotation.TableField;
import com.beicroon.construct.annotation.TableName;
import com.beicroon.construct.database.model.GenericModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("`file_file`")
public class FileFileModel extends GenericModel {

    @TableField(name = "`url`")
    @ApiModelProperty(name = "地址")
    private String url;

    @TableField(name = "`name`")
    @ApiModelProperty(name = "名称")
    private String name;

    @TableField(name = "`ext`")
    @ApiModelProperty(name = "后缀")
    private String ext;

    @TableField(name = "`size`")
    @ApiModelProperty(name = "大小")
    private Long size;

}
