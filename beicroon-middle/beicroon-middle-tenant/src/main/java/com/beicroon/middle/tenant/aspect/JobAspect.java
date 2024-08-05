package com.beicroon.middle.tenant.aspect;

import com.beicroon.construct.auth.utils.AuthUtils;
import com.beicroon.middle.tenant.handler.TenantJobHandler;
import com.beicroon.middle.tenant.manager.TenantIdManager;
import com.xxl.job.core.handler.annotation.XxlJob;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.MDC;
import org.springframework.context.annotation.Lazy;

import java.util.Set;

@Slf4j
@Aspect
public class JobAspect {

    @Lazy
    @Resource
    private TenantIdManager tenantIdManager;

    @Resource
    private TenantJobHandler tenantJobHandler;

    @Around("@annotation(xxlJob)")
    public Object around(ProceedingJoinPoint joinPoint, XxlJob xxlJob) {
        Set<Long> tenantIds = this.tenantIdManager.getTenantIds();

        for (Long tenantId : tenantIds) {
            MDC.clear();

            AuthUtils.clear();

            this.tenantJobHandler.handle(tenantId, joinPoint, xxlJob);
        }

        return null;
    }

}
