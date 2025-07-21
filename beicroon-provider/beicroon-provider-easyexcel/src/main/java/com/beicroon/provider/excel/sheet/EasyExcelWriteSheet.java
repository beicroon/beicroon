package com.beicroon.provider.excel.sheet;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.beicroon.construct.excel.sheet.IWriteSheet;

public class EasyExcelWriteSheet implements IWriteSheet<WriteSheet> {

    private final WriteSheet sheet;

    public EasyExcelWriteSheet() {
        this.sheet = EasyExcel.writerSheet().build();
    }

    public EasyExcelWriteSheet(Integer sheetIndex) {
        this.sheet = EasyExcel.writerSheet(sheetIndex).build();
    }

    public EasyExcelWriteSheet(String sheetName) {
        this.sheet = EasyExcel.writerSheet(sheetName).build();
    }

    @Override
    public WriteSheet getSheet() {
        return this.sheet;
    }

}
