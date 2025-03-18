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
@TableName("`home_banner`")
public class HomeBannerModel extends GenericModel {

    @TableField(name = "`url`")
    @ApiModelProperty(name = "地址")
    private String url;

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
