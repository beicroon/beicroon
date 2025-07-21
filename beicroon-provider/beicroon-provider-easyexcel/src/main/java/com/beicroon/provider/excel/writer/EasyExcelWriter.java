package com.beicroon.provider.excel.writer;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.beicroon.construct.excel.data.DataExcel;
import com.beicroon.construct.excel.enums.AlignmentEnums;
import com.beicroon.construct.excel.sheet.IWriteSheet;
import com.beicroon.construct.excel.writer.IExcelWriter;
import com.beicroon.construct.utils.EmptyUtils;
import com.beicroon.construct.utils.NumberUtils;
import com.beicroon.provider.excel.handler.EasyExcelCellWriteHandler;
import com.beicroon.provider.excel.handler.EasyExcelRowWriteHandler;
import com.beicroon.provider.excel.handler.EasyExcelSheetWriteHandler;
import com.beicroon.provider.excel.sheet.EasyExcelWriteSheet;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class EasyExcelWriter implements IExcelWriter<WriteSheet>, AutoCloseable {

    private final ByteArrayOutputStream stream;

    private final EasyExcelRowWriteHandler rowHandler;

    private final EasyExcelCellWriteHandler cellHandler;

    private final EasyExcelSheetWriteHandler sheetHandler;

    private final ExcelWriter writer;

    public EasyExcelWriter() {
        this.stream = new ByteArrayOutputStream();

        this.rowHandler = new EasyExcelRowWriteHandler();

        this.cellHandler = new EasyExcelCellWriteHandler();

        this.sheetHandler = new EasyExcelSheetWriteHandler();

        this.writer = EasyExcel.write(this.stream)
                .registerWriteHandler(this.rowHandler)
                .registerWriteHandler(this.cellHandler)
                .registerWriteHandler(this.sheetHandler)
                .build();
    }

    @Override
    public byte[] getBytes() {
        this.writer.finish();

        return this.stream.toByteArray();
    }

    @Override
    public IWriteSheet<WriteSheet> newSheet() {
        return new EasyExcelWriteSheet();
    }

    @Override
    public IWriteSheet<WriteSheet> newSheet(Integer sheetIndex) {
        return new EasyExcelWriteSheet(sheetIndex);
    }

    @Override
    public IWriteSheet<WriteSheet> newSheet(String sheetName) {
        return new EasyExcelWriteSheet(sheetName);
    }

    @Override
    public IExcelWriter<WriteSheet> write(DataExcel excel) {
        return this.write(excel, this.newSheet());
    }

    @Override
    public IExcelWriter<WriteSheet> write(DataExcel excel, Integer sheetIndex) {
        return this.write(excel, this.newSheet(sheetIndex));
    }

    @Override
    public IExcelWriter<WriteSheet> write(DataExcel excel, String sheetName) {
        return this.write(excel, this.newSheet(sheetName));
    }

    @Override
    public IExcelWriter<WriteSheet> write(DataExcel excel, IWriteSheet<WriteSheet> sheet) {
        this.rowHandler.putHeadHeight(excel.getHeadHeight());

        this.sheetHandler.setWidths(excel.getWidths());

        List<List<?>> values = excel.getValues(((dataRow, dataCell, row, col) -> {
            if (EmptyUtils.isInteger(dataCell.getRowspan()) && NumberUtils.gt(dataCell.getRowspan(), 1)) {
                this.rowHandler.addMergeRule(row, row + dataCell.getRowspan() - 1, col, col);
            }

            if (EmptyUtils.isInteger(dataCell.getColspan()) && NumberUtils.gt(dataCell.getColspan(), 1)) {
                this.rowHandler.addMergeRule(row, row, col, col + dataCell.getColspan() - 1);
            }

            AlignmentEnums alignment = AlignmentEnums.of(dataCell.getHorizontalAlignment(), dataCell.getVerticalAlignment());

            if (alignment != null) {
                this.cellHandler.addAlignment(col, row, alignment);
            }
            else if (dataRow.isHead()) {
                this.cellHandler.addAlignment(col, row, AlignmentEnums.CENTER_WRAP);
            }
        }));

        this.writer.write(values, sheet.getSheet());

        return this;
    }

    @Override
    public void close() {
        this.writer.finish();
    }

}
