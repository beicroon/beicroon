package com.beicroon.service.file.entity.filefile.dto;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.base.entity.IdDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(name = "编辑文件入参")
@EqualsAndHashCode(callSuper = true)
public class FileFileUpdateDTO extends IdDTO {

    @ApiModelProperty(name = "地址")
    private String url;

    @ApiModelProperty(name = "名称")
    private String name;

    @ApiModelProperty(name = "后缀")
    private String ext;

    @ApiModelProperty(name = "大小")
    private Long size;

}
