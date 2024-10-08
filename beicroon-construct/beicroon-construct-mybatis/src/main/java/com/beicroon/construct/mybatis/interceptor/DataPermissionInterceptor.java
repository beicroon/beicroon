package com.beicroon.construct.mybatis.interceptor;

import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.extension.plugins.inner.BaseMultiTableInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.beicroon.construct.database.annotation.DataPermission;
import com.beicroon.construct.database.annotation.DataScope;
import com.beicroon.construct.mybatis.expression.AdminExpression;
import com.beicroon.construct.mybatis.expression.OrgExpression;
import com.beicroon.construct.mybatis.expression.UserExpression;
import com.beicroon.construct.mybatis.helper.DataPermissionHelper;
import com.beicroon.construct.utils.EmptyUtils;
import com.beicroon.construct.utils.ListUtils;
import net.sf.jsqlparser.expression.Expression;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataPermissionInterceptor extends BaseMultiTableInnerInterceptor implements InnerInterceptor {

    private static final Map<String, List<DataPermission>> PERMISSION_CACHE = new HashMap<>();

    @Override
    public void beforeQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) {
        PluginUtils.MPBoundSql mpBs = PluginUtils.mpBoundSql(boundSql);

        mpBs.sql(this.parserSingle(mpBs.sql(), ms.getId()));
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
        List<DataPermission> permissions;

        if (!PERMISSION_CACHE.containsKey(whereSegment)) {
            PERMISSION_CACHE.put(whereSegment, permissions = DataPermissionHelper.getPermissions(whereSegment));
        } else {
            permissions = PERMISSION_CACHE.get(whereSegment);
        }

        if (EmptyUtils.isEmpty(permissions)) {
            return null;
        }

        Expression scopeWhere = null;

        for (DataPermission permission : permissions) {
            DataScope[] scopes = permission.value();

            if (EmptyUtils.isEmpty(scopes)) {
                continue;
            }

            for (DataScope dataScope : scopes) {
                switch (dataScope.value()) {
                    case ORG:
                        scopeWhere = DataPermissionHelper.getAndExpression(
                                scopeWhere, OrgExpression.getExpression(table, dataScope)
                        );
                        break;
                    case USER:
                        scopeWhere = DataPermissionHelper.getAndExpression(
                                scopeWhere, UserExpression.getExpression(table, dataScope)
                        );
                        break;
                    case ADMIN:
                        scopeWhere = DataPermissionHelper.getAndExpression(
                                scopeWhere, AdminExpression.getExpression(table, dataScope)
                        );
                        break;
                    default:
                        throw new RuntimeException(String.format("未支持的数据权限类型[%s]", dataScope.value()));
                }
            }
        }

        return scopeWhere;
    }

}
