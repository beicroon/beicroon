package com.beicroon.construct.entity;

import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.annotation.FieldSearch;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
public class QueryDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "当前页码")
    private Long pageNum;

    @ApiModelProperty(name = "每页条数")
    private Long pageSize;

    @ApiModelProperty(name = "查询内容")
    private String keywords;

    @ApiModelProperty(name = "选项卡编码")
    private String tabCode;

    @ApiModelProperty(name = "子选项卡编码")
    private String subTabCode;

    @FieldSearch(name = "`id`")
    @ApiModelProperty(name = "主键")
    private Long id;

    @FieldSearch(name = "`id`", in = true)
    @ApiModelProperty(name = "主键列表")
    private List<Long> ids;

    @FieldSearch(name = "`created_at`", between = true)
    @ApiModelProperty(name = "创建时间")
    private String createdAt;

    @FieldSearch(name = "`creator_id`")
    @ApiModelProperty(name = "创建人主键")
    private Long creatorId;

    @FieldSearch(name = "`creator_code`")
    @ApiModelProperty(name = "创建人编码")
    private String creatorCode;

    @FieldSearch(name = "`creator_name`", like = true)
    @ApiModelProperty(name = "创建人名称")
    private String creatorName;

    @JsonIgnore
    public Long getPageNumOrDefault() {
        if (this.pageNum == null) {
            return 1L;
        }

        return this.pageNum;
    }

    @JsonIgnore
    public Long getPageSizeOrDefault() {
        if (this.pageSize == null) {
            return 15L;
        }

        if (this.pageSize > 300) {
            return 300L;
        }

        return this.pageSize;
    }

}
