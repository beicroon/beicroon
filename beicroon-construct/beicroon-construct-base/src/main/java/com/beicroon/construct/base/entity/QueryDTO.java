package com.beicroon.construct.base.entity;

import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.annotation.FieldSearch;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
public class QueryDTO implements Serializable {

    @Serial
    private final static long serialVersionUID = 1L;

    @ApiModelProperty(name = "检索内容")
    private String keywords;

    @ApiModelProperty(name = "选项卡编码")
    private String tabCode;

    @ApiModelProperty(name = "子选项卡编码")
    private String subTabCode;

    @ApiModelProperty(name = "主键")
    @FieldSearch(name = "`id`")
    private Long id;

    @ApiModelProperty(name = "主键列表")
    @FieldSearch(name = "`id`", in = true)
    private List<Long> ids;

    @ApiModelProperty(name = "创建时间")
    @FieldSearch(name = "`created_at`", between = true)
    private String createdAt;

    @ApiModelProperty(name = "创建人主键")
    @FieldSearch(name = "`creator_id`")
    private Long creatorId;

    @ApiModelProperty(name = "创建人名称")
    @FieldSearch(name = "`creator_name`", like = true)
    private String creatorName;

    @ApiModelProperty(name = "是否禁用")
    private String disabledFlag;

    @ApiModelProperty(name = "分页信息")
    private PageInfo pageInfo;

    @Data
    public static class PageInfo implements Serializable {

        @Serial
        private final static long serialVersionUID = 1L;

        @ApiModelProperty(name = "当前页数")
        private Integer page;

        @ApiModelProperty(name = "当前条数")
        private Integer size;

    }

}
