package com.beicroon.construct.excel.writer;

import com.beicroon.construct.excel.data.DataExcel;
import com.beicroon.construct.excel.sheet.IWriteSheet;

public interface IExcelWriter<T> {

    byte[] getBytes();

    IWriteSheet<T> newSheet();

    IWriteSheet<T> newSheet(Integer sheetIndex);

    IWriteSheet<T> newSheet(String sheetName);

    IExcelWriter<T> write(DataExcel excel);

    IExcelWriter<T> write(DataExcel excel, Integer sheetIndex);

    IExcelWriter<T> write(DataExcel excel, String sheetName);

    IExcelWriter<T> write(DataExcel excel, IWriteSheet<T> sheet);

}
