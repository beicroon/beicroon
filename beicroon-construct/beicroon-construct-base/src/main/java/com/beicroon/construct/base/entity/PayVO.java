package com.beicroon.construct.base.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PayVO extends BaseVO {

    public PayVO(WechatPayVO wechatParams) {
        this.wechatParams = wechatParams;
    }

    @Schema(name = "wechatParams", description = "微信支付参数")
    private WechatPayVO wechatParams;

}
