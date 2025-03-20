package com.beicroon.service.file.entity.filefile.dto;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.annotation.FieldSearch;
import com.beicroon.construct.base.entity.QueryDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(name = "查询文件入参")
@EqualsAndHashCode(callSuper = true)
public class FileFileQueryDTO extends QueryDTO {

    @FieldSearch(name = "`url`")
    @ApiModelProperty(name = "地址")
    private String url;

    @FieldSearch(name = "`name`")
    @ApiModelProperty(name = "名称")
    private String name;

    @FieldSearch(name = "`ext`")
    @ApiModelProperty(name = "后缀")
    private String ext;

    @FieldSearch(name = "`size`")
    @ApiModelProperty(name = "大小")
    private Long size;

}
