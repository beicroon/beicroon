package com.beicroon.middle.tenant.manager;

import com.beicroon.common.tenant.rpc.feign.ITenantTenantFeignApi;
import com.beicroon.construct.json.utils.JsonUtils;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j
public class TenantIdManager {

    @Resource
    private ITenantTenantFeignApi tenantApi;

    private final Set<Long> tenantIds = new HashSet<>();

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private final Lock readLock = lock.readLock();

    private final Lock writeLock = lock.writeLock();

    public Set<Long> getTenantIds() {
        this.readLock.lock();

        try {
            return this.tenantIds;
        } finally {
            this.readLock.unlock();
        }
    }


    @PostConstruct
    public void init() {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            this.writeLock.lock();

            try {
                Set<Long> ids = this.tenantApi.listId().getData();

                log.info("租户主键更新:ids={}", JsonUtils.toJson(ids));

                this.tenantIds.clear();

                this.tenantIds.addAll(ids);
            } catch (Exception ex) {
                log.warn("租户主键获取异常", ex);
            } finally {
                this.writeLock.unlock();
            }
        }, 0, 60, TimeUnit.SECONDS);
    }

}
