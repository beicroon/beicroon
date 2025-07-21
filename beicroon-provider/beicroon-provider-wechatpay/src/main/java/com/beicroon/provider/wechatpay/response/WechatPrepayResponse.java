package com.beicroon.provider.wechatpay.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class WechatPrepayResponse implements Serializable {

    @Serial
    private final static long serialVersionUID = 1L;

    private String code;

    private String message;

    @JsonAlias("prepay_id")
    private String prepayId;

}
