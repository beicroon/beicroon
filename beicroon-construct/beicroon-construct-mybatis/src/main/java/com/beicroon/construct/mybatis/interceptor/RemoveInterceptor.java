package com.beicroon.construct.mybatis.interceptor;

import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.extension.plugins.inner.BaseMultiTableInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.beicroon.construct.constant.SystemConstant;
import com.beicroon.construct.utils.ListUtils;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.relational.IsNullExpression;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.update.Update;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

public class RemoveInterceptor extends BaseMultiTableInnerInterceptor implements InnerInterceptor {

    @Override
    public void beforeQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) {
        PluginUtils.MPBoundSql mpBs = PluginUtils.mpBoundSql(boundSql);

        mpBs.sql(this.parserSingle(mpBs.sql(), null));
    }

    protected void processInsert(Insert insert, int index, String sql, Object obj) {

    }

    protected void processDelete(Delete delete, int index, String sql, Object obj) {

    }

    protected void processUpdate(Update update, int index, String sql, Object obj) {

    }

    protected void processSelect(Select select, int index, String sql, Object obj) {
        String whereSegment = (String) obj;

        this.processSelectBody(select, whereSegment);

        ListUtils.foreach(select.getWithItemsList(), withItem -> this.processSelectBody(withItem, whereSegment));
    }

    @Override
    public Expression buildTableExpression(Table table, Expression where, String whereSegment) {
        IsNullExpression expression = new IsNullExpression();

        expression.setLeftExpression(new Column(table, SystemConstant.REMOVE_KEY_NAME));

        return expression;
    }

}
