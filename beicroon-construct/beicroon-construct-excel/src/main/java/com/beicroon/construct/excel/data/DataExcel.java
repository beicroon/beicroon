package com.beicroon.construct.excel.data;

import com.beicroon.construct.annotation.ExcelProperty;
import com.beicroon.construct.annotation.ExcelWidth;
import com.beicroon.construct.excel.enums.HorizontalAlignmentEnums;
import com.beicroon.construct.excel.enums.VerticalAlignmentEnums;
import com.beicroon.construct.excel.function.GetValueConsumer;
import com.beicroon.construct.utils.ClassUtils;
import com.beicroon.construct.utils.EmptyUtils;
import com.beicroon.construct.utils.ListUtils;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class DataExcel {

    private final int cellSize;

    private final int titleRow;

    private final Map<Integer, Short> headHeight = new HashMap<>();

    private final List<DataRow> titles;

    private final List<Short> widths;

    private final List<DataRow> rows;

    public DataExcel(int cellSize) {
        this(cellSize, 0, new ArrayList<>(), EmptyUtils.emptyList());
    }

    public DataExcel(int titleRow, List<String> title) {
        this(title.size(), titleRow, new ArrayList<>(), EmptyUtils.emptyList());

        this.titles.add(DataRow.of(title));
    }

    public DataExcel(int titleRow, List<String> title, List<Short> widths) {
        this(title.size(), titleRow, new ArrayList<>(), widths);

        this.titles.add(DataRow.of(title));
    }

    public DataExcel(int cellSize, int titleRow, DataRow title) {
        this(cellSize, titleRow, List.of(title), EmptyUtils.emptyList());
    }

    public DataExcel(int cellSize, int titleRow, DataRow title, List<Short> widths) {
        this(cellSize, titleRow, List.of(title), widths);
    }

    public DataExcel(int cellSize, int titleRow, List<DataRow> titles, List<Short> widths) {
        this.cellSize = cellSize;

        this.titleRow = titleRow;

        this.titles = titles;

        this.widths = widths;

        this.rows = new ArrayList<>();
    }

    public void putHeadHeight(int index, Short height) {
        this.headHeight.put(index, height);
    }

    public void addRow(DataRow row) {
        if (row.getCells().size() > this.cellSize) {
            throw new IllegalArgumentException("单元格数量超出最大限制");
        }

        this.rows.add(row);
    }

    public void addRow(Object value) {
        this.addRow(value, null, null, null, null);
    }

    public void addRow(Object value, HorizontalAlignmentEnums horizontalAlignment) {
        this.addRow(value, null, null, horizontalAlignment, null);
    }

    public void addRow(Object value, HorizontalAlignmentEnums horizontalAlignment, VerticalAlignmentEnums verticalAlignment) {
        this.addRow(value, null, null, horizontalAlignment, verticalAlignment);
    }

    public void addRow(Object value, Integer colspan) {
        this.addRow(value, null, colspan, null, null);
    }

    public void addRow(Object value, Integer colspan, HorizontalAlignmentEnums horizontalAlignment) {
        this.addRow(value, null, colspan, horizontalAlignment, null);
    }

    public void addRow(Object value, Integer colspan, HorizontalAlignmentEnums horizontalAlignment, VerticalAlignmentEnums verticalAlignment) {
        this.addRow(value, null, colspan, horizontalAlignment, verticalAlignment);
    }

    public void addRow(Object value, Integer rowspan, Integer colspan) {
        this.addRow(value, rowspan, colspan, null, null);
    }

    public void addRow(Object value, Integer rowspan, Integer colspan, HorizontalAlignmentEnums horizontalAlignment) {
        this.addRow(value, rowspan, colspan, horizontalAlignment, null);
    }

    public void addRow(Object value, Integer rowspan, Integer colspan, HorizontalAlignmentEnums horizontalAlignment, VerticalAlignmentEnums verticalAlignment) {
        DataRow row = DataRow.of();

        row.addCell(value, rowspan, colspan, horizontalAlignment, verticalAlignment);

        this.addRow(row);
    }

    public DataRow getRow(int index) {
        int size = this.getTitles().size() + this.getRows().size() - 1;

        if (index > size) {
            throw new IllegalArgumentException("行索引超出最大限制");
        }

        if (index < this.getTitleRow()) {
            return this.getRows().get(index);
        }

        int titleIndex = index - this.getTitleRow();

        if (titleIndex >= 0 && titleIndex < this.getTitles().size()) {
            DataRow row = this.getTitles().get(titleIndex);

            row.setHead(true);

            return row;
        }

        return this.getRows().get(index - this.getTitles().size());
    }

    public List<List<?>> getValues(GetValueConsumer consumer) {
        int size = this.getTitles().size() + this.getRows().size();

        List<List<?>> values = new ArrayList<>(size);

        AtomicInteger rowAtomic = new AtomicInteger(0);

        while (rowAtomic.get() < size) {
            int row = rowAtomic.getAndIncrement();

            DataRow dataRow = this.getRow(row);

            List<Object> rowValues = new ArrayList<>(this.getCellSize());

            ListUtils.foreach(dataRow.getCells(), (DataCell dataCell, Integer col) -> {
                consumer.accept(dataRow, dataCell, row, col);

                Object value = dataCell.getValue();

                if (value == null) {
                    rowValues.add(EmptyUtils.emptyString());
                } else {
                    rowValues.add(value);
                }
            });

            if (rowValues.size() < this.getCellSize()) {
                for (int i = 0; i < this.getCellSize() - rowValues.size(); i++) {
                    rowValues.add(EmptyUtils.emptyString());
                }
            }

            values.add(rowValues);
        }

        return values;
    }

    public static DataExcel of(Class<?> head) {
        return DataExcel.of(0, head);
    }

    public static DataExcel of(int titleRow, Class<?> head) {
        return DataExcel.of(titleRow, head, null);
    }

    public static DataExcel of(int titleRow, Class<?> head, Short headHeight) {
        DataRow row = DataRow.of();

        List<Short> widths = new ArrayList<>();

        ClassUtils.forAllField(head, field -> {
            ExcelProperty annotation = field.getAnnotation(ExcelProperty.class);

            if (annotation == null) {
                return;
            }

            ExcelWidth width = field.getAnnotation(ExcelWidth.class);

            if (width != null) {
                widths.add(width.value());
            }

            row.addCell(annotation.value());
        });

        DataExcel excel = new DataExcel(row.getCells().size(), titleRow, row, widths);

        if (headHeight != null) {
            excel.putHeadHeight(titleRow, headHeight);
        }

        return excel;
    }

}
