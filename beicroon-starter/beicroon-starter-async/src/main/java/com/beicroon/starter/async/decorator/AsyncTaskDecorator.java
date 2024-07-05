package com.beicroon.starter.async.decorator;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.TaskDecorator;

@Slf4j
public class AsyncTaskDecorator implements TaskDecorator {

    @Override
    public @NonNull Runnable decorate(@NonNull Runnable runnable) {
        return () -> {
            try {
                runnable.run();
            } finally {
            }
        };
    }

}
