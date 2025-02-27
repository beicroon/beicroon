package com.beicroon.construct.job.register;

import com.beicroon.construct.job.annotation.Job;
import com.beicroon.construct.job.annotation.Task;
import jakarta.annotation.Nonnull;
import lombok.Getter;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Method;
import java.util.Map;

@Getter
public abstract class JobRegister implements SmartInitializingSingleton, DisposableBean, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(@Nonnull ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterSingletonsInstantiated() {
        Map<String, Object> beans = this.getApplicationContext().getBeansWithAnnotation(Task.class);

        for (Object bean : beans.values()) {
            Method[] methods = bean.getClass().getDeclaredMethods();

            for (Method method : methods) {
                Job job = AnnotationUtils.findAnnotation(method, Job.class);

                if (job == null) {
                    continue;
                }

                this.register(bean, method, job);
            }
        }
    }

    @Override
    public void destroy() {

    }

    public abstract boolean enable();

    public abstract void register(Object bean, Method method, Job job);

}
