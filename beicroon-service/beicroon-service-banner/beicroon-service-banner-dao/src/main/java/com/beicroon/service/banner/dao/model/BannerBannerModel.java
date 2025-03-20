package com.beicroon.service.banner.dao.model;

import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.annotation.TableField;
import com.beicroon.construct.annotation.TableName;
import com.beicroon.construct.database.model.GenericModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("`banner_banner`")
public class BannerBannerModel extends GenericModel {

    @TableField(name = "`file_id`")
    @ApiModelProperty(name = "文件主键")
    private Long fileId;

    @TableField(name = "`file_name`")
    @ApiModelProperty(name = "文件名称")
    private String fileName;

    @TableField(name = "`file_url`")
    @ApiModelProperty(name = "文件地址")
    private String fileUrl;

    @TableField(name = "`description`")
    @ApiModelProperty(name = "描述")
    private String description;

    @TableField(name = "`valid_at`")
    @ApiModelProperty(name = "生效时间")
    private LocalDateTime validAt;

    @TableField(name = "`expired_at`")
    @ApiModelProperty(name = "过期时间")
    private LocalDateTime expiredAt;

}
