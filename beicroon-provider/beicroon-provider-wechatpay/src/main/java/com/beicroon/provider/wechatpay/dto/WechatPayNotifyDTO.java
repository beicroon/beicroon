package com.beicroon.provider.wechatpay.dto;

import com.beicroon.construct.base.entity.BaseDTO;
import com.fasterxml.jackson.annotation.JsonAlias;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class WechatPayNotifyDTO extends BaseDTO {

    @Schema(name = "id", description = "回调通知的唯一编号")
    private String id;

    @Schema(name = "createTime", description = "回调通知的创建时间")
    private LocalDateTime createTime;

    @Schema(name = "eventType", description = "微信支付回调通知的类型")
    private String eventType;

    @Schema(name = "resourceType", description = "通知的资源数据类型(固定为encrypt-resource)")
    private String resourceType;

    @Schema(name = "resource", description = "通知资源数据")
    private Resource resource;

    @Schema(name = "summary", description = "微信支付对回调内容的摘要备注")
    private String summary;

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class Resource extends BaseDTO {

        @Schema(name = "algorithm", description = "回调数据密文的加密算法类型")
        private String algorithm;

        @Schema(name = "ciphertext", description = "Base64编码后的回调数据密文(需Base64解码并使用APIV3密钥解密)")
        private String ciphertext;

        @JsonAlias("associated_data")
        @Schema(name = "associatedData", description = "参与解密的附加数据(该字段可能为空)")
        private String associatedData;

        @JsonAlias("original_type")
        @Schema(name = "originalType", description = "加密前的对象类型(为transaction)")
        private String originalType;

        @Schema(name = "nonce", description = "参与解密的随机串")
        private String nonce;

    }

}
