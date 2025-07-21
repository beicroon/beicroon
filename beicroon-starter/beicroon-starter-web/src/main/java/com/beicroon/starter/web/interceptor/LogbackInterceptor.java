package com.beicroon.starter.web.interceptor;

import com.beicroon.construct.constant.HeaderConstant;
import com.beicroon.construct.constant.SystemConstant;
import com.beicroon.construct.utils.EmptyUtils;
import com.beicroon.construct.utils.RandomUtils;
import com.beicroon.construct.utils.UrlUtils;
import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class LogbackInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull Object handler) {
        String traceId = request.getHeader(HeaderConstant.TRACE_ID);

        if (EmptyUtils.isEmpty(traceId)) {
            MDC.put(SystemConstant.TRACE_ID, RandomUtils.uuid());
        } else {
            MDC.put(SystemConstant.TRACE_ID, UrlUtils.decode(traceId));
        }

        return true;
    }

    @Override
    public void afterCompletion(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull Object handler, @Nullable Exception ex) {
        MDC.clear();
    }

}
