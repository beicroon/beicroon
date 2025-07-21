package com.beicroon.provider.mybatisplus.interceptor;

import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.extension.plugins.inner.BaseMultiTableInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.beicroon.construct.auth.utils.AuthUtils;
import com.beicroon.construct.database.annotation.DataPermission;
import com.beicroon.construct.database.annotation.DataScope;
import com.beicroon.construct.database.enums.ScopeTypeEnums;
import com.beicroon.construct.utils.EmptyUtils;
import com.beicroon.provider.mybatisplus.helper.MybatisPlusHelper;
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

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DataPermissionInterceptor extends BaseMultiTableInnerInterceptor implements InnerInterceptor {

    private final static Map<String, List<DataPermission>> PERMISSION_CACHE = new ConcurrentHashMap<>();

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
        this.processSelectBody(select, (String) obj);
    }

    @Override
    public Expression buildTableExpression(Table table, Expression where, String whereSegment) {
        List<DataPermission> permissions;

        if (!PERMISSION_CACHE.containsKey(whereSegment)) {
            PERMISSION_CACHE.put(whereSegment, permissions = MybatisPlusHelper.parsePermissions(whereSegment));
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

            for (DataScope scope : scopes) {
                if (ScopeTypeEnums.CREATOR.equals(scope.value())) {
                    scopeWhere = MybatisPlusHelper.getEqLongExpression(table, scope, AuthUtils.getUserId(), scopeWhere);
                } else {
                    throw new RuntimeException(String.format("未支持的数据权限类型[%s]", scope.value()));
                }
            }
        }

        return scopeWhere;
    }

}
