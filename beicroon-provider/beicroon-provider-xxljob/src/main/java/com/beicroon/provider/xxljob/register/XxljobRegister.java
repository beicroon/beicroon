package com.beicroon.provider.xxljob.register;

import com.beicroon.construct.job.annotation.Job;
import com.beicroon.construct.job.register.JobRegister;
import com.beicroon.construct.utils.EmptyUtils;
import com.beicroon.provider.xxljob.manager.XxlJobManager;
import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import com.xxl.job.core.handler.impl.MethodJobHandler;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

@Slf4j
@Configuration
public class XxljobRegister extends JobRegister {

    @Resource
    private XxlJobManager xxlJobManager;

    @Override
    public boolean enable() {
        return this.xxlJobManager.getXxlJobExecutor() != null;
    }

    @Override
    public void register(Object bean, Method method, Job job) {
        try {
            Method init = EmptyUtils.isEmpty(job.init()) ? null : bean.getClass().getMethod(job.init());

            Method destroy = EmptyUtils.isEmpty(job.destroy()) ? null : bean.getClass().getMethod(job.destroy());

            XxlJobSpringExecutor.registJobHandler(job.value(), new MethodJobHandler(bean, method, init, destroy));
        } catch (NoSuchMethodException ex) {
            throw new RuntimeException(ex);
        }
    }

}
