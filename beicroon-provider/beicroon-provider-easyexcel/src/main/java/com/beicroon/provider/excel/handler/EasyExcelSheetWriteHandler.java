package com.beicroon.provider.excel.handler;

import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import com.beicroon.construct.utils.ListUtils;
import lombok.Setter;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

@Setter
public class EasyExcelSheetWriteHandler implements SheetWriteHandler {

    private List<Short> widths;

    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
        Sheet sheet = writeSheetHolder.getSheet();

        ListUtils.foreach(this.widths, (Short width, Integer i) -> {
            sheet.setColumnWidth(i, width * 256);
        });
    }

}
