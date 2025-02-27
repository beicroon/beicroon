package com.beicroon.construct.excel.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AlignmentEnums {
    HORIZONTAL_CENTER("horizontal-center", "水平居中"),
    VERTICAL_CENTER("vertical-center", "垂直居中"),
    CENTER("center", "居中"),
    CENTER_WRAP("center-wrap", "居中换行"),
    ;

    private final String code;

    private final String name;

    public static AlignmentEnums of(HorizontalAlignmentEnums horizontalAlignment, VerticalAlignmentEnums verticalAlignment) {
        if (HorizontalAlignmentEnums.CENTER == horizontalAlignment && VerticalAlignmentEnums.CENTER == verticalAlignment) {
            return AlignmentEnums.CENTER;
        } else if (HorizontalAlignmentEnums.CENTER == horizontalAlignment) {
            return AlignmentEnums.HORIZONTAL_CENTER;
        } else if (VerticalAlignmentEnums.CENTER == verticalAlignment) {
            return AlignmentEnums.VERTICAL_CENTER;
        }

        return null;
    }

}
