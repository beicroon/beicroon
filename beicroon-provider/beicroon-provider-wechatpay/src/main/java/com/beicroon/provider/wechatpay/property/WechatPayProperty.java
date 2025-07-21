package com.beicroon.provider.wechatpay.property;

import com.beicroon.construct.http.enums.HttpMethodEnums;
import com.beicroon.construct.utils.EmptyUtils;
import com.beicroon.construct.utils.RSAUtils;
import com.beicroon.construct.utils.RandomUtils;
import com.beicroon.construct.utils.TimeUtils;
import com.beicroon.provider.wechatpay.vo.AuthorizationVO;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.PrivateKey;
import java.security.PublicKey;

@Data
@Component
public class WechatPayProperty {

    private static final String HOST = "https://api.mch.weixin.qq.com";

    private static final String PATH_ORDER = "/v3/pay/transactions/jsapi";

    @Value("${beicroon.domain:http://localhost:8080}")
    private String domain;

    @Value("${beicroon.wechat.appid}")
    private String appId;

    @Value("${beicroon.wechat.pay.mchid}")
    private String mchid;

    @Value("${beicroon.wechat.pay.serial-no}")
    private String serialNo;

    @Value("${beicroon.wechat.pay.api-v3-key}")
    private String apiV3Key;

    @Value("${beicroon.wechat.pay.public-key-path}")
    private String publicKeyPath;

    @Value("${beicroon.wechat.pay.private-key-path}")
    private String privateKeyPath;

    private PublicKey publicKey;

    private PrivateKey privateKey;

    public String getOrderPath() {
        return PATH_ORDER;
    }

    public String getOrderUrl() {
        return String.format("%s%s", HOST, PATH_ORDER);
    }

    public PrivateKey getPrivateKey() {
        if (this.privateKey == null) {
            this.privateKey = RSAUtils.loadPrivateKey(this.initStream(this.getPrivateKeyPath()));
        }

        return this.privateKey;
    }

    public PublicKey getPublicKey() {
        if (this.publicKey == null) {
            this.publicKey = RSAUtils.loadCertificatePublicKey(this.initStream(this.getPublicKeyPath()));
        }

        return this.publicKey;
    }

    public AuthorizationVO getAuthorization(HttpMethodEnums method, String urlPath, String bodyJson) {
        AuthorizationVO authorization = new AuthorizationVO();

        authorization.setNonce(RandomUtils.uuid());
        authorization.setTimestamp(String.valueOf(TimeUtils.currentTimestamp()));

        String message = method.getName() + "\n" +
                         urlPath + "\n" +
                         authorization.getTimestamp() + "\n" +
                         authorization.getNonce() + "\n" +
                         bodyJson + "\n";

        String signature = RSAUtils.sign(message, this.getPrivateKey());

        String token = String.format(
                "WECHATPAY2-SHA256-RSA2048 mchid=\"%s\",nonce_str=\"%s\",timestamp=\"%s\",serial_no=\"%s\",signature=\"%s\"",
                this.getMchid(), authorization.getNonce(), authorization.getTimestamp(), this.getSerialNo(), signature
        );

        authorization.setToken(token);

        return authorization;
    }

    public String getPaySign(String timestamp, String nonce, String packageValue) {
        String message = this.getAppId() + "\n" +
                         timestamp + "\n" +
                         nonce + "\n" +
                         packageValue + "\n";

        return RSAUtils.sign(message, this.getPrivateKey());
    }

    private InputStream initStream(String path) {
        try {
            if (path.startsWith("classpath:")) {
                String realpath = path.replace("classpath:", EmptyUtils.emptyString());

                return new ClassPathResource(realpath).getInputStream();
            } else {
                String realpath = path.replace("filepath:", EmptyUtils.emptyString());

                return new FileInputStream(realpath);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

}
