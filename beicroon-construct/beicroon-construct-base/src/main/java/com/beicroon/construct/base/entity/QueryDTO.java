package com.beicroon.construct.base.entity;

import com.beicroon.construct.annotation.FieldSearch;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
public class QueryDTO implements Serializable {

    @Serial
    private final static long serialVersionUID = 1L;

    @Schema(name = "keywords", description = "检索内容")
    private String keywords;

    @Schema(name = "tabCode", description = "选项卡编码")
    private String tabCode;

    @Schema(name = "subTabCode", description = "子选项卡编码")
    private String subTabCode;

    @FieldSearch(value = "`id`")
    @Schema(name = "id", description = "主键")
    private Long id;

    @FieldSearch(value = "`id`", ne = true)
    @Schema(name = "neId", description = "排除主键")
    private Long neId;

    @FieldSearch(value = "`id`", in = true)
    @Schema(name = "ids", description = "主键列表")
    private List<Long> ids;

    @FieldSearch(value = "`created_at`", between = true)
    @Schema(name = "createdAt", description = "创建时间(用~分隔开始和结束时间)")
    private String createdAt;

    @FieldSearch(value = "`creator_id`")
    @Schema(name = "creatorId", description = "创建人主键")
    private Long creatorId;

    @FieldSearch(value = "`creator_name`", like = true)
    @Schema(name = "creatorName", description = "创建人名称")
    private String creatorName;

    @Schema(name = "disabledFlag", description = "是否禁用")
    private String disabledFlag;

    @Schema(name = "pageInfo", description = "分页信息")
    private PageInfo pageInfo = new PageInfo(1, 30);

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PageInfo implements Serializable {

        @Serial
        private final static long serialVersionUID = 1L;

        @Schema(name = "page", description = "当前页数")
        private Integer page;

        @Schema(name = "size", description = "当前条数")
        private Integer size;

    }

}
