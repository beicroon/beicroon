package com.beicroon.construct.base.entity;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(name = "code", description = "状态码")
    private Integer code;

    @Schema(name = "message", description = "消息")
    private String message;

    @Schema(name = "data", description = "数据")
    private T data;

    @Schema(name = "pageInfo", description = "分页")
    private PageInfo pageInfo;

    @Schema(name = "voiceInfo", description = "语音")
    private VoiceInfo voiceInfo;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PageInfo implements Serializable {

        @Serial
        private final static long serialVersionUID = 1L;

        @Schema(name = "page", description = "当前页码")
        private long page = 1;

        @Schema(name = "size", description = "每页条数")
        private long size = 30;

        @Schema(name = "total", description = "总条数")
        private long total = 0;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VoiceInfo implements Serializable {

        @Serial
        private final static long serialVersionUID = 1L;

        @Schema(name = "type", description = "消息类型")
        private String type;

        @Schema(name = "message", description = "消息内容")
        private String message;

    }

}
