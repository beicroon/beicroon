package com.beicroon.provider.excel.handler;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import com.beicroon.construct.excel.enums.AlignmentEnums;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EasyExcelCellWriteHandler implements CellWriteHandler {

    private CellStyle horizontalCenterStyle;

    private CellStyle verticalCenterStyle;

    private CellStyle centerStyle;

    private CellStyle centerWrapStyle;

    private final Map<Integer, Map<Integer, AlignmentEnums>> alignmentMap = new HashMap<>();

    public void addAlignment(Integer columnIndex, Integer rowIndex, AlignmentEnums alignment) {
        if (!this.alignmentMap.containsKey(columnIndex)) {
            this.alignmentMap.put(columnIndex, new HashMap<>());
        }

        this.alignmentMap.get(columnIndex).put(rowIndex, alignment);
    }

    @Override
    public void afterCellDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, List<WriteCellData<?>> cellDataList, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
        if (cell != null) {
            if (this.alignmentMap.containsKey(cell.getColumnIndex())) {
                Map<Integer, AlignmentEnums> rowAlignmentMap = this.alignmentMap.get(cell.getColumnIndex());

                if (rowAlignmentMap.containsKey(cell.getRowIndex())) {
                    AlignmentEnums alignment = rowAlignmentMap.get(cell.getRowIndex());

                    // 水平居中
                    if (alignment == AlignmentEnums.HORIZONTAL_CENTER) {
                        cell.setCellStyle(this.getHorizontalCenterStyle(writeSheetHolder));
                    }
                    // 垂直居中
                    else if (alignment == AlignmentEnums.VERTICAL_CENTER) {
                        cell.setCellStyle(this.getVerticalCenterStyle(writeSheetHolder));
                    }
                    // 居中
                    else if (alignment == AlignmentEnums.CENTER) {
                        cell.setCellStyle(this.getCenterStyle(writeSheetHolder));
                    }
                    // 居中换行
                    else if (alignment == AlignmentEnums.CENTER_WRAP) {
                        cell.setCellStyle(this.getCenterWrapStyle(writeSheetHolder));
                    }
                }
            }
        }
    }

    public CellStyle getHorizontalCenterStyle(WriteSheetHolder writeSheetHolder) {
        if (this.horizontalCenterStyle == null) {
            this.horizontalCenterStyle = writeSheetHolder.getSheet().getWorkbook().createCellStyle();
            this.horizontalCenterStyle.setAlignment(HorizontalAlignment.CENTER);
        }

        return horizontalCenterStyle;
    }

    public CellStyle getVerticalCenterStyle(WriteSheetHolder writeSheetHolder) {
        if (this.verticalCenterStyle == null) {
            this.verticalCenterStyle = writeSheetHolder.getSheet().getWorkbook().createCellStyle();
            this.verticalCenterStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        }

        return this.verticalCenterStyle;
    }

    public CellStyle getCenterStyle(WriteSheetHolder writeSheetHolder) {
        if (this.centerStyle == null) {
            this.centerStyle = writeSheetHolder.getSheet().getWorkbook().createCellStyle();
            this.centerStyle.setAlignment(HorizontalAlignment.CENTER);
            this.centerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        }

        return this.centerStyle;
    }

    public CellStyle getCenterWrapStyle(WriteSheetHolder writeSheetHolder) {
        if (this.centerWrapStyle == null) {
            this.centerWrapStyle = writeSheetHolder.getSheet().getWorkbook().createCellStyle();
            this.centerWrapStyle.setAlignment(HorizontalAlignment.CENTER);
            this.centerWrapStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            this.centerWrapStyle.setWrapText(true);
        }

        return this.centerWrapStyle;
    }

}
