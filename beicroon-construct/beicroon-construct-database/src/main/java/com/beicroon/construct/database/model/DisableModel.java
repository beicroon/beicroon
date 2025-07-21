package com.beicroon.construct.database.model;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class DisableModel extends GenericModel {

    @TableField(value = "`disabled_at`")
    @Schema(name = "disabledAt", description = "禁用时间")
    private LocalDateTime disabledAt;

    @TableField(value = "`disabled_id`")
    @Schema(name = "disabledId", description = "禁用人主键")
    private Long disabledId;

    @TableField(value = "`disabled_code`")
    @Schema(name = "disabledCode", description = "禁用人编码")
    private String disabledCode;

    @TableField(value = "`disabled_name`")
    @Schema(name = "disabledName", description = "禁用人名称")
    private String disabledName;

}
