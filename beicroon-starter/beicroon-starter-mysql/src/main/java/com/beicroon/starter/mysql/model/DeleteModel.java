package com.beicroon.starter.mysql.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.beicroon.construct.annotation.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class DeleteModel extends GenericModel {

    @TableField(value = "`deleted_flag`")
    @TableLogic(value = "2", delval = "1")
    @ApiModelProperty(name = "删除标识(1:已删除,2:未删除)")
    private Integer deletedFlag;

}
