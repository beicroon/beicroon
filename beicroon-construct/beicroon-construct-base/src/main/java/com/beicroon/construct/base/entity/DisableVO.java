package com.beicroon.construct.base.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class DisableVO extends GenericVO {

    @Schema(name = "disabledAt", description = "禁用时间")
    private LocalDateTime disabledAt;

    @Schema(name = "disabledId", description = "禁用人主键")
    private Long disabledId;

    @Schema(name = "disabledCode", description = "禁用人编码")
    private String disabledCode;

    @Schema(name = "disabledName", description = "禁用人名称")
    private String disabledName;

}
