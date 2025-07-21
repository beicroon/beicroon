package com.beicroon.starter.async.config;

import com.beicroon.starter.async.decorator.AsyncTaskDecorator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class ExecutorConfig {

    @Value("${task.executor.name-prefix:beicroon-task-executor}")
    private String namePrefix;

    @Value("${task.executor.core-pool-size:10}")
    private int corePoolSize;

    @Value("${task.executor.max-pool-size:300}")
    private int maxPoolSize;

    @Value("${task.executor.queue-capacity:10}")
    private int queueCapacity;

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setThreadNamePrefix(this.namePrefix);
        executor.setCorePoolSize(this.corePoolSize);
        executor.setMaxPoolSize(this.maxPoolSize);
        executor.setQueueCapacity(this.queueCapacity);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setTaskDecorator(new AsyncTaskDecorator());

        executor.initialize();

        return executor;
    }

}
