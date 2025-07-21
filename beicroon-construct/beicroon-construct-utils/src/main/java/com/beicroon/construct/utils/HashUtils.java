package com.beicroon.construct.utils;

import com.beicroon.construct.constant.SystemConstant;

import java.nio.charset.Charset;
import java.security.*;
import java.util.Base64;
import java.util.zip.CRC32;

public final class HashUtils {

    private final static String MD5 = "MD5";

    private final static String SHA_256 = "SHA-256";

    private final static String SHA_256_WITH_RSA = "SHA256withRSA";

    private HashUtils() {

    }

    public static String md5(String value) {
        return HashUtils.md5(value, SystemConstant.CHARSET);
    }

    public static String md5(String value, Charset charset) {
        return HashUtils.md5(value.getBytes(charset));
    }

    public static String md5(byte[] value) {
        return HashUtils.getHash(value, MD5);
    }

    public static String sha256(String value) {
        return HashUtils.sha256(value, SystemConstant.CHARSET);
    }

    public static String sha256(String value, Charset charset) {
        return HashUtils.sha256(value.getBytes(charset));
    }

    public static String sha256(byte[] value) {
        return HashUtils.getHash(value, SHA_256);
    }

    public static String sha256WithRsa(String value, PrivateKey privateKey) {
        byte[] bytes;

        try {
            Signature signature = Signature.getInstance(SHA_256_WITH_RSA);

            signature.initSign(privateKey);

            signature.update(value.getBytes(SystemConstant.CHARSET));

            bytes = signature.sign();
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException ex) {
            throw new RuntimeException(ex);
        }

        return HashUtils.base64Encode(bytes);
    }

    public static String getHash(byte[] value, String name) {
        StringBuilder hash = new StringBuilder();

        MessageDigest digest;

        try {
            digest = MessageDigest.getInstance(name);
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }

        for (byte b : digest.digest(value)) {
            hash.append(Integer.toHexString((b & 0xFF) | 0x100), 1, 3);
        }

        return hash.toString();
    }

    public static Long crc32(String value) {
        return HashUtils.crc32(value, SystemConstant.CHARSET);
    }

    public static Long crc32(String value, Charset charset) {
        return HashUtils.crc32(value.getBytes(charset));
    }

    public static Long crc32(byte[] value) {
        CRC32 crc32 = new CRC32();

        crc32.reset();

        crc32.update(value, 0, value.length);

        return crc32.getValue();
    }

    public static String base64Encode(String value) {
        return HashUtils.base64Encode(value, SystemConstant.CHARSET);
    }

    public static String base64Encode(String value, Charset charset) {
        return HashUtils.base64Encode(value.getBytes(charset));
    }

    public static String base64Encode(byte[] value) {
        return HashUtils.base64Encode(value, SystemConstant.CHARSET);
    }

    public static String base64Encode(byte[] value, Charset charset) {
        return new String(Base64.getEncoder().encode(value), charset);
    }

    public static String base64Decode(String value) {
        return HashUtils.base64Decode(value, SystemConstant.CHARSET);
    }

    public static String base64Decode(String value, Charset charset) {
        return HashUtils.base64Decode(value.getBytes(charset));
    }

    public static String base64Decode(byte[] value) {
        return HashUtils.base64Decode(value, SystemConstant.CHARSET);
    }

    public static String base64Decode(byte[] value, Charset charset) {
        return new String(Base64.getDecoder().decode(value), charset);
    }

    public static byte[] base64DecodeToBytes(String value) {
        return Base64.getDecoder().decode(value);
    }

    public static String getPasswordHash(String password, String salt) {
        return HashUtils.sha256(salt + password + salt);
    }

    public static boolean isPassword(String hash, String password, String salt) {
        return !hash.equals(HashUtils.sha256(salt + password + salt));
    }

}
