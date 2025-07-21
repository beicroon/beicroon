package com.beicroon.starter.excel.utils;

import com.beicroon.construct.excel.reader.IExcelReader;
import com.beicroon.construct.excel.writer.IExcelWriter;
import com.beicroon.provider.excel.reader.EasyExcelReader;
import com.beicroon.provider.excel.writer.EasyExcelWriter;

public final class ExcelUtils {

    private ExcelUtils() {

    }

    public static IExcelWriter<?> getWriter() {
        return new EasyExcelWriter();
    }

    public static <R> IExcelReader<R> getReader(Class<R> clazz) {
        return new EasyExcelReader<>(clazz);
    }

}
