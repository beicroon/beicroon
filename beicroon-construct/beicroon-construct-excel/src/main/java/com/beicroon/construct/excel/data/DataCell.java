package com.beicroon.construct.excel.data;

import com.beicroon.construct.excel.enums.HorizontalAlignmentEnums;
import com.beicroon.construct.excel.enums.VerticalAlignmentEnums;
import lombok.Data;

@Data
public class DataCell {

    private Object value;

    private Integer rowspan;

    private Integer colspan;

    private HorizontalAlignmentEnums horizontalAlignment;

    private VerticalAlignmentEnums verticalAlignment;

    public DataCell(Object value) {
        this(value, null, null, null, null);
    }

    public DataCell(Object value, HorizontalAlignmentEnums horizontalAlignment) {
        this(value, null, null, horizontalAlignment, null);
    }

    public DataCell(Object value, HorizontalAlignmentEnums horizontalAlignment, VerticalAlignmentEnums verticalAlignment) {
        this(value, null, null, horizontalAlignment, verticalAlignment);
    }

    public DataCell(Object value, Integer rowspan) {
        this(value, rowspan, null, null, null);
    }

    public DataCell(Object value, Integer rowspan, HorizontalAlignmentEnums horizontalAlignment) {
        this(value, rowspan, null, horizontalAlignment, null);
    }

    public DataCell(Object value, Integer rowspan, HorizontalAlignmentEnums horizontalAlignment, VerticalAlignmentEnums verticalAlignment) {
        this(value, rowspan, null, horizontalAlignment, verticalAlignment);
    }

    public DataCell(Object value, Integer rowspan, Integer colspan) {
        this(value, rowspan, colspan, null, null);
    }

    public DataCell(Object value, Integer rowspan, Integer colspan, HorizontalAlignmentEnums horizontalAlignment) {
        this(value, rowspan, colspan, horizontalAlignment, null);
    }

    public DataCell(Object value, Integer rowspan, Integer colspan, HorizontalAlignmentEnums horizontalAlignment, VerticalAlignmentEnums verticalAlignment) {
        this.value = value;

        this.rowspan = rowspan;

        this.colspan = colspan;

        this.horizontalAlignment = horizontalAlignment;

        this.verticalAlignment = verticalAlignment;
    }

}
