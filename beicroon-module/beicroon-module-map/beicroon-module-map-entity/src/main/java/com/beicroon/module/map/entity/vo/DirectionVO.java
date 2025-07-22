package com.beicroon.module.map.entity.vo;

import com.beicroon.construct.base.entity.BaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DirectionVO extends BaseVO {

    @Schema(name = "distance", description = "距离(米)")
    private Integer distance;

    @Schema(name = "duration", description = "时间(秒)")
    private Integer duration;

}
