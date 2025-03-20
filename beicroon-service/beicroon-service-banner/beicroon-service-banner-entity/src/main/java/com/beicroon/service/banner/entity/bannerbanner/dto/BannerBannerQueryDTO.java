package com.beicroon.service.banner.entity.bannerbanner.dto;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.annotation.FieldSearch;
import com.beicroon.construct.base.entity.QueryDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@ApiModel(name = "查询轮播图入参")
@EqualsAndHashCode(callSuper = true)
public class BannerBannerQueryDTO extends QueryDTO {

    @FieldSearch(name = "`file_id`")
    @ApiModelProperty(name = "文件主键")
    private Long fileId;

    @FieldSearch(name = "`file_name`")
    @ApiModelProperty(name = "文件名称")
    private String fileName;

    @FieldSearch(name = "`file_url`")
    @ApiModelProperty(name = "文件地址")
    private String fileUrl;

    @FieldSearch(name = "`description`")
    @ApiModelProperty(name = "描述")
    private String description;

    @FieldSearch(name = "`valid_at`")
    @ApiModelProperty(name = "生效时间")
    private LocalDateTime validAt;

    @FieldSearch(name = "`expired_at`")
    @ApiModelProperty(name = "过期时间")
    private LocalDateTime expiredAt;

}
