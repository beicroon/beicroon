package com.beicroon.module.base.entity.baseconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import com.beicroon.construct.base.entity.GenericVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaseConfigBaseVO extends GenericVO {

    @Schema(name = "configKey", description = "键")
    private String configKey;

    @Schema(name = "configValue", description = "值")
    private String configValue;

    @Schema(name = "configRemark", description = "备注")
    private String configRemark;

    @Schema(name = "componentName", description = "组件名称")
    private String componentName;

    @Schema(name = "componentWidth", description = "组件宽度")
    private String componentWidth;

    @Schema(name = "componentRequired", description = "组件是否必填")
    private String componentRequired;

    @Schema(name = "componentMultiple", description = "组件是否多选")
    private String componentMultiple;

}
