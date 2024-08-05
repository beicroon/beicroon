package com.beicroon.middle.tenant.Interceptor;

import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.beicroon.construct.auth.utils.AuthUtils;
import com.beicroon.construct.utils.EmptyUtils;
import com.beicroon.middle.tenant.property.TenantProperty;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;

import java.util.HashSet;
import java.util.Set;

public class TenantInterceptor implements TenantLineHandler {

    private final Set<String> ignoreTables = new HashSet<>();

    public TenantInterceptor(TenantProperty properties) {
        ignoreTables.add("flyway_schema");
        ignoreTables.add("flyway_schema_history");
        ignoreTables.addAll(properties.getIgnoreTables());
    }

    @Override
    public String getTenantIdColumn() {
        return "`tenant_id`";
    }

    @Override
    public Expression getTenantId() {
        if (!EmptyUtils.isId(AuthUtils.getTenantId())) {
            return new LongValue(0);
        }

        return new LongValue(AuthUtils.getTenantId());
    }

    @Override
    public boolean ignoreTable(String tableName) {
        if (this.ignoreTables.contains(tableName)) {
            return true;
        }

        return !EmptyUtils.isId(AuthUtils.getTenantId());
    }

}
