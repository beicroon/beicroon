package com.beicroon.construct.utils;

import javax.crypto.Cipher;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

public final class RSAUtils {

    private RSAUtils() {

    }

    public static String encrypt(String data, PublicKey publicKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");

            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            byte[] encryptedBytes = cipher.doFinal(data.getBytes());

            return HashUtils.base64Encode(encryptedBytes);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String decrypt(String encryptedData, PrivateKey privateKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");

            cipher.init(Cipher.DECRYPT_MODE, privateKey);

            byte[] decryptedBytes = cipher.doFinal(HashUtils.base64DecodeToBytes(encryptedData));

            return new String(decryptedBytes);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String sign(String data, PrivateKey privateKey) {
        try {
            Signature signature = Signature.getInstance("SHA256withRSA");

            signature.initSign(privateKey);

            signature.update(data.getBytes());

            byte[] signatureBytes = signature.sign();

            return HashUtils.base64Encode(signatureBytes);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static boolean verify(String data, String signature, PublicKey publicKey) {
        try {
            Signature sign = Signature.getInstance("SHA256withRSA");

            sign.initVerify(publicKey);

            sign.update(data.getBytes());

            byte[] signatureBytes = HashUtils.base64DecodeToBytes(signature);

            return sign.verify(signatureBytes);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
