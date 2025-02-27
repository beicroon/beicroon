package com.beicroon.construct.excel.function;

import com.beicroon.construct.excel.data.DataCell;
import com.beicroon.construct.excel.data.DataRow;

public interface GetValueConsumer {

    void accept(DataRow dataRow, DataCell dataCell, int row, int col);

}
