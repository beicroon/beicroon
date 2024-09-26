package com.beicroon.construct.entity;

import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.interfaces.IAuthUser;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
public class AuthUser implements IAuthUser, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "主键")
    private Long id = 0L;

    @ApiModelProperty(name = "角色")
    private String role = "";

    @ApiModelProperty(name = "编码")
    private String code = "";

    @ApiModelProperty(name = "昵称")
    private String name = "";

    @ApiModelProperty(name = "权限")
    private Permission permission = new Permission();

    @Data
    public static class Permission implements Serializable {

        @Serial
        private static final long serialVersionUID = 1L;

        @ApiModelProperty(name = "所有组织")
        private String allOrg;

        @ApiModelProperty(name = "组织主键")
        private List<Long> orgIds;

    }

}
