package com.beicroon.construct.base.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class WechatPayVO extends BaseVO {

    @Schema(name = "appid", description = "应用ID")
    private String appid;

    @JsonProperty("timeStamp")
    @Schema(name = "timeStamp", description = "时间戳")
    private String timestamp;

    @JsonProperty("nonceStr")
    @Schema(name = "nonceStr", description = "随机字符串")
    private String nonce;

    @JsonProperty("package")
    @Schema(name = "package", description = "预支付标识")
    private String packageValue;

    @Schema(name = "signType", description = "签名类型")
    private String signType = "RSA";

    @Schema(name = "paySign", description = "支付签名")
    private String paySign;

}
