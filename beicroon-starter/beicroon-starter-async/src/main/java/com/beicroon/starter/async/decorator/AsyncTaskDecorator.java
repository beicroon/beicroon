package com.beicroon.starter.async.decorator;

import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.TaskDecorator;

@Slf4j
public class AsyncTaskDecorator implements TaskDecorator {

    @Override
    public @Nonnull Runnable decorate(@Nonnull Runnable runnable) {
        return () -> {
            try {
                runnable.run();
            } finally {
            }
        };
    }

}
