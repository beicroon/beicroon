package com.beicroon.module.file.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FileTypeEnums {
    TEXT("普通文档", "text", null, null),
    IMAGE("图片", "image", "image", null),
    WORD("文本文档", "word", null, null),
    EXCEL("表格", "excel", null, null),
    PPT("演示文档", "ppt", null, null),
    UNKNOW("未知", "unknow", null, null),
    ;

    private final String name;

    private final String code;

    private final String prefix;

    private final String suffix;

}
