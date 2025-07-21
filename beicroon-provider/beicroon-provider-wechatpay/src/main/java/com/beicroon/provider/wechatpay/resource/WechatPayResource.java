package com.beicroon.provider.wechatpay.resource;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class WechatPayResource implements Serializable {

    @Serial
    private final static long serialVersionUID = 1L;

    private String mchid;

    @JsonAlias("out_trade_no")
    private String outTradeNo;

    private String appid;

}
