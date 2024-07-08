package com.beicroon.service.admin.controller.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/admin")
public class AdminAccountController {

    @GetMapping("/account-show")
    public String show(@RequestParam("id") Long id) {
        return String.valueOf(id);
    }

}
