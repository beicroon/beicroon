package com.beicroon.provider.xxljob.manager;

import com.beicroon.construct.utils.EmptyUtils;
import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class XxlJobManager {

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

    public XxlJobSpringExecutor getXxlJobExecutor() {
        if (EmptyUtils.isEmpty(this.adminAddresses)) {
            log.warn("XXL-JOB警告：未配置 xxl.job.admin.addresses 跳过执行器初始化");

            return null;
        }

        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();

        xxlJobSpringExecutor.setAdminAddresses(this.adminAddresses);
        xxlJobSpringExecutor.setAppname(String.format("%s-%s", this.appName, this.activeProfile));
        xxlJobSpringExecutor.setAccessToken(this.accessToken);
        xxlJobSpringExecutor.setLogPath(this.logPath);
        xxlJobSpringExecutor.setLogRetentionDays(this.logRetentionDays);

        return xxlJobSpringExecutor;
    }
}
