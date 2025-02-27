package com.beicroon.construct.excel.data;

import com.beicroon.construct.excel.enums.HorizontalAlignmentEnums;
import com.beicroon.construct.excel.enums.VerticalAlignmentEnums;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Data
public class DataRow {

    private boolean isHead = false;

    private final List<DataCell> cells = new ArrayList<>();

    private HorizontalAlignmentEnums horizontalAlignment;

    private VerticalAlignmentEnums verticalAlignment;

    public DataRow() {

    }

    public DataRow(HorizontalAlignmentEnums horizontalAlignment) {
        this.horizontalAlignment = horizontalAlignment;
    }

    public DataRow(HorizontalAlignmentEnums horizontalAlignment, VerticalAlignmentEnums verticalAlignment) {
        this.horizontalAlignment = horizontalAlignment;

        this.verticalAlignment = verticalAlignment;
    }

    public void addCell(Object value) {
        this.addCell(value, null, null, null, null);
    }

    public void addCell(Object value, HorizontalAlignmentEnums horizontalAlignment) {
        this.addCell(value, null, null, horizontalAlignment, null);
    }

    public void addCell(Object value, HorizontalAlignmentEnums horizontalAlignment, VerticalAlignmentEnums verticalAlignment) {
        this.addCell(value, null, null, horizontalAlignment, verticalAlignment);
    }

    public void addCell(Object value, Integer rowspan) {
        this.addCell(value, rowspan, null, null, null);
    }

    public void addCell(Object value, Integer rowspan, HorizontalAlignmentEnums horizontalAlignment) {
        this.addCell(value, rowspan, null, horizontalAlignment, null);
    }

    public void addCell(Object value, Integer rowspan, HorizontalAlignmentEnums horizontalAlignment, VerticalAlignmentEnums verticalAlignment) {
        this.addCell(value, rowspan, null, horizontalAlignment, verticalAlignment);
    }

    public void addCell(Object value, Integer rowspan, Integer colspan) {
        this.addCell(value, rowspan, colspan, null, null);
    }

    public void addCell(Object value, Integer rowspan, Integer colspan, HorizontalAlignmentEnums horizontalAlignment) {
        this.addCell(value, rowspan, colspan, horizontalAlignment, null);
    }

    public void addCell(Object value, Integer rowspan, Integer colspan, HorizontalAlignmentEnums horizontalAlignment, VerticalAlignmentEnums verticalAlignment) {
        DataCell cell = new DataCell(value, rowspan, colspan, horizontalAlignment, verticalAlignment);

        if (horizontalAlignment == null && this.horizontalAlignment != null) {
            cell.setHorizontalAlignment(this.horizontalAlignment);
        }

        if (verticalAlignment == null && this.verticalAlignment != null) {
            cell.setVerticalAlignment(this.verticalAlignment);
        }

        this.cells.add(cell);
    }

    public static DataRow of(Collection<?> values) {
        return DataRow.of(values, null, null);
    }

    public static DataRow of(Collection<?> values, HorizontalAlignmentEnums horizontalAlignment) {
        return DataRow.of(values, horizontalAlignment, null);
    }

    public static DataRow of(Collection<?> values, HorizontalAlignmentEnums horizontalAlignment, VerticalAlignmentEnums verticalAlignment) {
        DataRow row = new DataRow(horizontalAlignment, verticalAlignment);

        values.forEach(row::addCell);

        return row;
    }

    public static DataRow of(Object... values) {
        return DataRow.of(Arrays.asList(values));
    }

    public static DataRow of(HorizontalAlignmentEnums horizontalAlignment) {
        return DataRow.of(horizontalAlignment, null);
    }

    public static DataRow of(HorizontalAlignmentEnums horizontalAlignment, VerticalAlignmentEnums verticalAlignment) {
        return new DataRow(horizontalAlignment, verticalAlignment);
    }

}
