package com.beicroon.service.admin.entity.adminresource.vo;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@ApiModel(name = "查询资源树状出参")
@EqualsAndHashCode(callSuper = true)
public class AdminResourceTreeVO extends AdminResourceBaseVO {

    @ApiModelProperty(name = "子级列表")
    private List<AdminResourceTreeVO> children = new ArrayList<>();

}
