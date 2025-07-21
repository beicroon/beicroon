package com.beicroon.provider.wechatpay.vo;

import com.beicroon.construct.base.entity.BaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AuthorizationVO extends BaseVO {

    @Schema(name = "timestamp", description = "时间戳")
    private String timestamp;

    @Schema(name = "nonce", description = "随机字符串")
    private String nonce;

    @Schema(name = "token", description = "令牌")
    private String token;

    @Schema(name = "prepayId", description = "预支付标识")
    private String prepayId;;

}
