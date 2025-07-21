package com.beicroon.starter.pay.manager;

import com.beicroon.provider.wechatpay.handler.WechatPayHandler;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class PayManager {

    @Resource
    private WechatPayHandler wechatPayHandler;

}
