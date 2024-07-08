package com.beicroon.service.admin.controller.admin;

import com.beicroon.construct.entity.Result;
import com.beicroon.construct.entity.WebResult;
import com.beicroon.service.admin.entity.account.vo.AccountBaseVO;
import com.beicroon.service.admin.service.impl.AccountService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/admin")
public class AdminAccountController {

    @Resource
    private AccountService accountService;

    @GetMapping("/account-show")
    public Result<AccountBaseVO> show(@RequestParam("id") Long id) {
        return WebResult.success(this.accountService.show(id));
    }

}
