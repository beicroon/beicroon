package com.beicroon.common.tenant.rpc.feign;

import com.beicroon.construct.annotation.ApiOperation;
import com.beicroon.construct.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Set;

@FeignClient(name = "beicroon-service-tenant", path = "/api/feign/tenant")
public interface ITenantTenantFeignApi {

    @ApiOperation(name = "获取所有租户主键")
    @GetMapping("/tenant-id-list")
    Result<Set<Long>> idList();

}
