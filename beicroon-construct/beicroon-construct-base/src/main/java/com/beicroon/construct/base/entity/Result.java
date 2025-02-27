package com.beicroon.construct.base.entity;

import com.beicroon.construct.annotation.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class Result<T> implements Serializable {

    @Serial
    private final static long serialVersionUID = 1L;

    @ApiModelProperty(name = "状态码")
    private Integer code;

    @ApiModelProperty(name = "消息")
    private String message;

    @ApiModelProperty(name = "数据")
    private T data;

    @ApiModelProperty(name = "分页")
    private PageInfo pageInfo;

    @ApiModelProperty(name = "语音")
    private VoiceInfo voiceInfo;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PageInfo implements Serializable {

        @Serial
        private final static long serialVersionUID = 1L;

        @ApiModelProperty(name = "当前页码")
        private long page = 1;

        @ApiModelProperty(name = "每页条数")
        private long size = 30;

        @ApiModelProperty(name = "总条数")
        private long total = 0;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VoiceInfo implements Serializable {

        @Serial
        private final static long serialVersionUID = 1L;

        @ApiModelProperty(name = "消息类型")
        private String type;

        @ApiModelProperty(name = "消息内容")
        private String message;

    }

}
