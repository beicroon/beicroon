package com.beicroon.middle.tenant.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import com.beicroon.middle.tenant.Interceptor.TenantInterceptor;
import com.beicroon.middle.tenant.aspect.JobAspect;
import com.beicroon.middle.tenant.manager.TenantIdManager;
import com.beicroon.middle.tenant.property.TenantProperty;
import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import jakarta.annotation.Resource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableConfigurationProperties(TenantProperty.class)
@Import({TenantInterceptor.class, TenantIdManager.class, JobAspect.class})
@ConditionalOnProperty(prefix = "beicroon.tenant", value = "enable", matchIfMissing = true)
public class TenantConfig {

    @Resource
    private MybatisPlusInterceptor interceptor;

    @Bean
    public TenantLineInnerInterceptor tenantLineInnerInterceptor(TenantProperty properties) {
        TenantLineInnerInterceptor inner = new TenantLineInnerInterceptor(new TenantInterceptor(properties));

        List<InnerInterceptor> inners = new ArrayList<>(this.interceptor.getInterceptors());

        inners.add(0, inner);

        this.interceptor.setInterceptors(inners);

        return inner;
    }

    @Bean
    @ConditionalOnBean(XxlJobSpringExecutor.class)
    public JobAspect jobAspect() {
        return new JobAspect();
    }

}
