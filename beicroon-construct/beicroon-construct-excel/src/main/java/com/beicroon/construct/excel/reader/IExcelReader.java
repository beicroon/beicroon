package com.beicroon.construct.excel.reader;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public interface IExcelReader<R> {

    default List<R> read(InputStream stream, BiConsumer<R, Map<String, String>> mapper) {
        return this.read(1, null, stream, mapper);
    }

    List<R> read(Integer head, Integer sheet, InputStream stream, BiConsumer<R, Map<String, String>> mapper);

}
