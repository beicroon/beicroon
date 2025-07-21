package com.beicroon.construct.utils;

import com.beicroon.construct.constant.SystemConstant;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public final class RSAUtils {

    private static final String RSA_ALGORITHM = "RSA";

    private static final String SHA_ALGORITHM = "SHA256withRSA";

    private RSAUtils() {

    }

    public static PrivateKey loadPrivateKey(InputStream stream) {
        try {
            String key = new String(stream.readAllBytes(), SystemConstant.CHARSET)
                    .replace("-----BEGIN PRIVATE KEY-----", EmptyUtils.emptyString())
                    .replace("-----END PRIVATE KEY-----", EmptyUtils.emptyString())
                    .replaceAll("\\s+", EmptyUtils.emptyString());

            byte[] bytes = HashUtils.base64DecodeToBytes(key);

            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(bytes);

            KeyFactory factory = KeyFactory.getInstance(RSA_ALGORITHM);

            return factory.generatePrivate(spec);
        } catch (Exception e) {
            throw new RuntimeException("无法加载私钥", e);
        }
    }

    public static PublicKey loadPublicKey(InputStream stream) {
        try {
            String cert = new String(stream.readAllBytes(), SystemConstant.CHARSET)
                    .replace("-----BEGIN CERTIFICATE-----", EmptyUtils.emptyString())
                    .replace("-----END CERTIFICATE-----", EmptyUtils.emptyString())
                    .replaceAll("\\s+", EmptyUtils.emptyString());

            byte[] bytes = HashUtils.base64DecodeToBytes(cert);

            X509EncodedKeySpec spec = new X509EncodedKeySpec(bytes);

            return KeyFactory.getInstance(RSA_ALGORITHM).generatePublic(spec);
        } catch (Exception e) {
            throw new RuntimeException("无法加载公钥", e);
        }
    }

    public static PublicKey loadCertificatePublicKey(InputStream stream) {
        try {
            CertificateFactory factory = CertificateFactory.getInstance("X.509");

            Certificate certificate = factory.generateCertificate(stream);

            return certificate.getPublicKey();
        } catch (Exception e) {
            throw new RuntimeException("无法从证书加载公钥", e);
        }
    }

    public static String encrypt(String data, PublicKey publicKey) {
        try {
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);

            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            byte[] bytes = cipher.doFinal(data.getBytes());

            return HashUtils.base64Encode(bytes);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String decrypt(String data, PrivateKey privateKey) {
        try {
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);

            cipher.init(Cipher.DECRYPT_MODE, privateKey);

            return new String(cipher.doFinal(HashUtils.base64DecodeToBytes(data)));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String wechatDecrypt(String key, String data, String nonce, String ciphertext) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(SystemConstant.CHARSET), "AES");

            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

            GCMParameterSpec spec = new GCMParameterSpec(128, nonce.getBytes(SystemConstant.CHARSET));

            cipher.init(Cipher.DECRYPT_MODE, keySpec, spec);

            if (data != null) {
                cipher.updateAAD(data.getBytes(SystemConstant.CHARSET));
            }

            byte[] decodedCiphertext = HashUtils.base64DecodeToBytes(ciphertext);

            byte[] plainText = cipher.doFinal(decodedCiphertext);

            return new String(plainText, SystemConstant.CHARSET);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String sign(String data, PrivateKey privateKey) {
        try {
            Signature signature = Signature.getInstance(SHA_ALGORITHM);

            signature.initSign(privateKey);

            signature.update(data.getBytes());

            return HashUtils.base64Encode(signature.sign());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static boolean verify(String data, String signature, PublicKey publicKey) {
        try {
            Signature sign = Signature.getInstance(SHA_ALGORITHM);

            sign.initVerify(publicKey);

            sign.update(data.getBytes());

            return sign.verify(HashUtils.base64DecodeToBytes(signature));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
