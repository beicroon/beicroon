package com.beicroon.construct.xxljob.config;

import com.beicroon.construct.utils.EmptyUtils;
import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class XxlJobConfig {

    @Value("${spring.profiles.active:dev}")
    private String activeProfile;

    @Value("${spring.application.name:}")
    private String appName;

    @Value("${xxl.job.admin.addresses:}")
    private String adminAddresses;

    @Value("${xxl.job.accessToken:}")
    private String accessToken;

    @Value("${xxl.job.executor.logPath:/app/logs/xxl-job}")
    private String logPath;

    @Value("${xxl.job.executor.logRetentionDays:30}")
    private int logRetentionDays;

    @Bean
    public XxlJobSpringExecutor xxlJobExecutor() {
        if (EmptyUtils.isEmpty(this.adminAddresses)) {
            return null;
        }

        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();

        xxlJobSpringExecutor.setAdminAddresses(this.adminAddresses + "/xxl-job-admin");
        xxlJobSpringExecutor.setAppname(String.format("%s-%s", this.appName, this.activeProfile));
        xxlJobSpringExecutor.setAccessToken(this.accessToken);
        xxlJobSpringExecutor.setLogPath(this.logPath);
        xxlJobSpringExecutor.setLogRetentionDays(this.logRetentionDays);

        return xxlJobSpringExecutor;
    }
}
