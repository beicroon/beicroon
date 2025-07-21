package com.beicroon.module.base.dao.model;

import io.swagger.v3.oas.annotations.media.Schema;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.beicroon.construct.database.model.GenericModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "`base_config`")
public class BaseConfigModel extends GenericModel {

    @TableField(value = "`config_key`")
    @Schema(name = "configKey", description = "键")
    private String configKey;

    @TableField(value = "`config_value`")
    @Schema(name = "configValue", description = "值")
    private String configValue;

    @TableField(value = "`config_remark`")
    @Schema(name = "configRemark", description = "备注")
    private String configRemark;

    @TableField(value = "`component_name`")
    @Schema(name = "componentName", description = "组件名称")
    private String componentName;

    @TableField(value = "`component_width`")
    @Schema(name = "componentWidth", description = "组件宽度")
    private String componentWidth;

    @TableField(value = "`component_required`")
    @Schema(name = "componentRequired", description = "组件是否必填")
    private String componentRequired;

    @TableField(value = "`component_multiple`")
    @Schema(name = "componentMultiple", description = "组件是否多选")
    private String componentMultiple;

}
