package com.beicroon.common.admin.rpc.feign;

import com.beicroon.common.admin.entity.account.admin.vo.AccountAdminBaseVO;
import com.beicroon.construct.annotation.ApiOperation;
import com.beicroon.construct.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "beicroon-service-admin", path = "/api/feign/admin")
public interface IAccountAdminFeignApi {

    @ApiOperation("新增映射")
    @GetMapping("/account-admin-show")
    Result<AccountAdminBaseVO> show(@RequestParam("id") Long id);

}
