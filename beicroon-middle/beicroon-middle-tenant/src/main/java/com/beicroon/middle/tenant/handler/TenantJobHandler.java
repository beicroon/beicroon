package com.beicroon.middle.tenant.handler;

import com.beicroon.construct.auth.utils.AuthUtils;
import com.beicroon.construct.constant.SystemConstant;
import com.beicroon.construct.utils.RandomUtils;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TenantJobHandler {

    @Async
    public void handle(Long tenantId, ProceedingJoinPoint joinPoint, XxlJob xxlJob) {
        MDC.put(SystemConstant.TRACE_ID, RandomUtils.uuid());

        AuthUtils.setTenantId(tenantId);

        try {
            joinPoint.proceed();
        } catch (Throwable ex) {
            log.error("定时任务[{}]执行异常", xxlJob.value(), ex);
        } finally {
            MDC.clear();

            AuthUtils.clear();
        }
    }

}
