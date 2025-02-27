package com.beicroon.starter.async.decorator;

import com.beicroon.construct.auth.entity.AuthThread;
import com.beicroon.construct.auth.utils.AuthUtils;
import com.beicroon.construct.constant.SystemConstant;
import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;

@Slf4j
public class AsyncTaskDecorator implements TaskDecorator {

    @Override
    public @Nonnull Runnable decorate(@Nonnull Runnable runnable) {
        String traceId = MDC.get(SystemConstant.TRACE_ID);

        AuthThread threadInfo = AuthUtils.getAuthThreadInfo();

        return () -> {
            MDC.put(SystemConstant.TRACE_ID, traceId);

            AuthUtils.setAuthThreadInfo(threadInfo);

            try {
                runnable.run();
            } finally {
                MDC.clear();

                AuthUtils.clear();
            }
        };
    }

}
