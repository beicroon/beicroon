package com.beicroon.service.rbac.entity.rbacauth.vo;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@ApiModel(name = "登录认证资源出参")
public class RbacAuthResourceVO implements Serializable {

    @Serial
    private final static long serialVersionUID = 1L;

    @ApiModelProperty(name = "父级主键")
    private Long parentId;

    @ApiModelProperty(name = "编码")
    private String code;

    @ApiModelProperty(name = "名称")
    private String name;

    @ApiModelProperty(name = "路由")
    private String path;

    @ApiModelProperty(name = "子级列表")
    private List<RbacAuthResourceVO> children = new ArrayList<>();

}
