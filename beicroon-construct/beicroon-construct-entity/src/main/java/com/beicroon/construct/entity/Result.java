package com.beicroon.construct.entity;

import com.beicroon.construct.annotation.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class Result<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "状态码")
    private Integer code;

    @ApiModelProperty(name = "消息内容")
    private String message;

    @ApiModelProperty(name = "数据内容")
    private T data;

    @ApiModelProperty(name = "分页内容")
    private Page page;

    @ApiModelProperty(name = "语音内容")
    private Voice voice;

    @JsonIgnore
    public boolean hasError() {
        return this.code != null && this.code > 0;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Page {

        @ApiModelProperty(name = "当前页码")
        private long num = 1;

        @ApiModelProperty(name = "每页条数")
        private long size = 30;

        @ApiModelProperty(name = "总条数")
        private long total = 0;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Voice {

        @ApiModelProperty(name = "消息类型")
        private String type;

        @ApiModelProperty(name = "消息内容")
        private String message;

    }

}
