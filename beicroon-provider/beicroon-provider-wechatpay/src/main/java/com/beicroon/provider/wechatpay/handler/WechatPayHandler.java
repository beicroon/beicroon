package com.beicroon.provider.wechatpay.handler;

import com.beicroon.construct.exception.utils.ExceptionUtils;
import com.beicroon.construct.http.entity.Http;
import com.beicroon.construct.http.enums.HttpMethodEnums;
import com.beicroon.construct.http.utils.HttpUtils;
import com.beicroon.construct.json.utils.JsonUtils;
import com.beicroon.construct.pay.exception.WechatNotifyException;
import com.beicroon.construct.utils.EmptyUtils;
import com.beicroon.construct.utils.NumberUtils;
import com.beicroon.construct.utils.RSAUtils;
import com.beicroon.provider.wechatpay.dto.WechatPayNotifyDTO;
import com.beicroon.provider.wechatpay.property.WechatPayProperty;
import com.beicroon.provider.wechatpay.resource.WechatPayResource;
import com.beicroon.provider.wechatpay.response.WechatPrepayResponse;
import com.beicroon.provider.wechatpay.vo.AuthorizationVO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class WechatPayHandler {

    private final static String NOTIFY_URL = "/api/open/pay-order-wechat-notify";

    @Value("${spring.profiles.active:local}")
    private String activeProfile;

    @Resource
    private WechatPayProperty property;

    public String getNotifyUrl() {
        return String.format("%s%s", this.property.getDomain(), NOTIFY_URL);
    }

    public AuthorizationVO prepay(String openid, BigDecimal amount, String description, Long id) {
        return this.prepay(openid, amount, description, String.valueOf(id));
    }

    public AuthorizationVO prepay(String openid, BigDecimal amount, String description, String outTradeCode) {
        Map<String, Object> amountMap = new HashMap<>();

        if ("pro".equals(this.activeProfile)) {
            amountMap.put("total", NumberUtils.multiply(amount, 100).intValue());
        } else {
            amountMap.put("total", 1);
        }

        amountMap.put("currency", "CNY");

        Map<String, Object> params = new HashMap<>();

        params.put("appid", this.property.getAppId());
        params.put("mchid", this.property.getMchid());
        params.put("description", description);
        params.put("out_trade_no", outTradeCode);
        params.put("notify_url", this.getNotifyUrl());
        params.put("amount", amountMap);
        params.put("payer", Map.of("openid", openid));

        Map<String, String> headers = new HashMap<>();

        AuthorizationVO authorization = this.property.getAuthorization(
                HttpMethodEnums.POST, this.property.getOrderPath(), JsonUtils.toJson(params)
        );

        headers.put("Authorization", authorization.getToken());

        Http http = HttpUtils.post(this.property.getOrderUrl(), params, headers);

        WechatPrepayResponse res = http.getResponseAs(WechatPrepayResponse.class);

        if (EmptyUtils.isNotEmpty(res.getCode())) {
            throw ExceptionUtils.business(String.format("微信支付下单错误：%s", res.getMessage()));
        }

        authorization.setPrepayId(res.getPrepayId());

        return authorization;
    }

    public WechatPayResource getNotifyResource(WechatPayNotifyDTO dto, String signature) {
        if (RSAUtils.verify(JsonUtils.toJson(dto), signature, this.property.getPublicKey())) {
            throw new WechatNotifyException("签名验证失败");
        }

        WechatPayNotifyDTO.Resource resourceDTO = dto.getResource();

        String dataString = RSAUtils.wechatDecrypt(
                this.property.getApiV3Key(),
                resourceDTO.getAssociatedData(),
                resourceDTO.getNonce(),
                resourceDTO.getCiphertext()
        );

        log.info("微信支付回调数据: {}", dataString);

        return JsonUtils.toObject(dataString, WechatPayResource.class);
    }

}
