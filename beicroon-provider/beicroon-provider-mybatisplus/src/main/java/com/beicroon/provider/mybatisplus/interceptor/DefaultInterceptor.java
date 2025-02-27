package com.beicroon.provider.mybatisplus.interceptor;

import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.extension.plugins.inner.BaseMultiTableInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.beicroon.construct.database.content.TableContent;
import com.beicroon.construct.database.interceptor.DatabaseInterceptor;
import com.beicroon.construct.utils.ListUtils;
import com.beicroon.provider.mybatisplus.helper.MybatisPlusHelper;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.update.Update;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.springframework.stereotype.Component;

import java.sql.Connection;

@Component
public class DefaultInterceptor extends BaseMultiTableInnerInterceptor implements InnerInterceptor, DatabaseInterceptor {

    @Override
    public void beforePrepare(StatementHandler sh, Connection connection, Integer transactionTimeout) {
        PluginUtils.MPStatementHandler mpSh = PluginUtils.mpStatementHandler(sh);

        MappedStatement ms = mpSh.mappedStatement();

        PluginUtils.MPBoundSql mpBs = mpSh.mPBoundSql();

        mpBs.sql(this.parserSingle(mpBs.sql(), ms.getId()));
    }

    protected void processInsert(Insert insert, int index, String sql, Object obj) {
        this.setTableName(insert.getTable());
    }

    protected void processDelete(Delete delete, int index, String sql, Object obj) {
        this.setTableName(delete.getTable());
    }

    protected void processUpdate(Update update, int index, String sql, Object obj) {
        this.setTableName(update.getTable());
    }

    protected void processSelect(Select select, int index, String sql, Object obj) {
        String whereSegment = (String) obj;

        this.processSelectBody(select, whereSegment);

        ListUtils.foreach(select.getWithItemsList(), withItem -> this.processSelectBody(withItem, whereSegment));
    }

    @Override
    public Expression buildTableExpression(Table table, Expression where, String whereSegment) {
        this.setTableName(table);

        return MybatisPlusHelper.getRemoveExpression();
    }

    private void setTableName(Table table) {
        TableInfo tableInfo = TableInfoHelper.getTableInfo(table.getName());

        TableContent tableContent = TableContent.getTableContent(tableInfo.getEntityType());

        table.setName(tableContent.getTableName());
    }

}
