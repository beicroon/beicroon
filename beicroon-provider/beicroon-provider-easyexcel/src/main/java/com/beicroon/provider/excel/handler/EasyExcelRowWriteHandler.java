package com.beicroon.provider.excel.handler;

import com.alibaba.excel.write.handler.RowWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EasyExcelRowWriteHandler implements RowWriteHandler {

    private final List<CellRangeAddress> mergeRules = new ArrayList<>();

    private final Map<Integer, Short> headHeight = new HashMap<>();

    public void putHeadHeight(Map<Integer, Short> headHeight) {
        this.headHeight.putAll(headHeight);
    }

    public void putHeadHeight(int row, short height) {
        this.headHeight.put(row, height);
    }

    public void addMergeRule(int firstRow, int lastRow, int firstCol, int lastCol) {
        this.mergeRules.add(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));
    }

    @Override
    public void afterRowDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row, Integer relativeRowIndex, Boolean isHead) {
        for (CellRangeAddress mergeRule : this.mergeRules) {
            writeSheetHolder.getSheet().addMergedRegionUnsafe(mergeRule);
        }

        if (row != null) {
            if (this.headHeight.containsKey(row.getRowNum())) {
                row.setHeight((short) (this.headHeight.get(row.getRowNum()) * 20));
            } else {
                row.setHeight((short) (20 * 20));
            }
        }
    }

}
