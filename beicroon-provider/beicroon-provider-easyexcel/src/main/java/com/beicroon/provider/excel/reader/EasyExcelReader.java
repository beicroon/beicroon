package com.beicroon.provider.excel.reader;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.beicroon.construct.annotation.ExcelProperty;
import com.beicroon.construct.excel.helper.ExcelHelper;
import com.beicroon.construct.excel.reader.IExcelReader;
import com.beicroon.construct.utils.ClassUtils;
import com.beicroon.construct.utils.EmptyUtils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public class EasyExcelReader<R> implements IExcelReader<R>, AutoCloseable {

    private final Class<R> clazz;

    private final ExcelReaderBuilder builder;

    private ExcelReader reader;

    public EasyExcelReader(Class<R> clazz) {
        this.clazz = clazz;

        this.builder = EasyExcel.read();
    }

    @Override
    public List<R> read(Integer head, Integer sheet, InputStream stream, BiConsumer<R, Map<String, String>> mapper) {
        List<R> res = new ArrayList<>();

        this.builder.file(stream);
        this.builder.autoTrim(true);

        if (EmptyUtils.isInteger(head)) {
            this.builder.headRowNumber(head);
        }

        if (EmptyUtils.isInteger(sheet)) {
            this.builder.sheet(sheet);
        }

        this.builder.registerReadListener(new AnalysisEventListener<Map<Integer, String>>() {

            private Map<Integer, String> header;

            @Override
            public void invokeHeadMap(Map<Integer, String> head, AnalysisContext context) {
                this.header = head;
            }

            @Override
            public void invoke(Map<Integer, String> line, AnalysisContext analysisContext) {
                Map<String, String> data = new HashMap<>(line.size());

                for (Map.Entry<Integer, String> entry : line.entrySet()) {
                    String key = this.header.get(entry.getKey());

                    if (EmptyUtils.isEmpty(key)) {
                        continue;
                    }

                    data.put(key, entry.getValue());
                }

                R r;

                try {
                    r = clazz.getDeclaredConstructor().newInstance();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

                ClassUtils.forAllField(clazz, field -> {
                    field.setAccessible(true);

                    ExcelProperty excelProperty = field.getAnnotation(ExcelProperty.class);

                    if (excelProperty == null) {
                        return;
                    }

                    Object value = ExcelHelper.getValue(data.get(excelProperty.value()), field);

                    try {
                        field.set(r, value);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                });

                mapper.accept(r, data);

                res.add(r);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {

            }

        });

        this.reader = this.builder.build();

        this.reader.readAll();

        return res;
    }

    @Override
    public void close() {
        if (this.reader == null) {
            return;
        }

        this.reader.close();
    }

}
