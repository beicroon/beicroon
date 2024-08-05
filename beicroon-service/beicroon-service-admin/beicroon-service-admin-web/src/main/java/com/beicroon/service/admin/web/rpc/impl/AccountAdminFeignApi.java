package com.beicroon.service.admin.web.rpc.impl;

import com.beicroon.common.admin.entity.account.admin.vo.AccountAdminBaseVO;
import com.beicroon.common.admin.rpc.feign.IAccountAdminFeignApi;
import com.beicroon.construct.entity.Result;
import com.beicroon.construct.entity.WebResult;
import com.beicroon.service.admin.web.service.IAccountAdminService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/feign/admin")
public class AccountAdminFeignApi implements IAccountAdminFeignApi {

    @Resource
    private IAccountAdminService accountAdminService;

    @Override
    public Result<AccountAdminBaseVO> show(Long id) {
        return WebResult.success(this.accountAdminService.show(id));
    }

}
