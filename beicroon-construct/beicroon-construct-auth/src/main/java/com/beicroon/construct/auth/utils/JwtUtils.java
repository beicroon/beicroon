package com.beicroon.construct.auth.utils;

import com.beicroon.construct.base.entity.AuthUser;
import com.beicroon.construct.base.interfaces.IAuthUser;
import com.beicroon.construct.constant.JwtConstant;
import com.beicroon.construct.enums.AuthRoleEnums;
import com.beicroon.construct.exception.utils.ExceptionUtils;
import com.beicroon.construct.utils.NumberUtils;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import java.text.ParseException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public final class JwtUtils {

    private final static Map<Integer, String> SALT = new LinkedHashMap<>();

    static {
        JwtUtils.SALT.put(10, "B");
        JwtUtils.SALT.put(30, "E");
        JwtUtils.SALT.put(50, "I");
        JwtUtils.SALT.put(70, "C");
        JwtUtils.SALT.put(90, "R");
        JwtUtils.SALT.put(110, "O");
        JwtUtils.SALT.put(130, "O");
        JwtUtils.SALT.put(150, "N");
    }

    private JwtUtils() {

    }

    public static String getSaltString() {
        return SALT.values().toString();
    }

    private static String addSalt(String code) {
        StringBuilder sb = new StringBuilder(code);

        int i = 0;

        for (Map.Entry<Integer, String> slat : SALT.entrySet()) {
            if (slat.getKey() > sb.length()) {
                break;
            }

            sb.insert(slat.getKey() + i, slat.getValue());

            i++;
        }

        return sb.toString();
    }

    private static String cleanSalt(String code) {
        StringBuilder sb = new StringBuilder(code);

        try {
            for (Map.Entry<Integer, String> slat : SALT.entrySet()) {
                if (slat.getKey() > sb.length()) {
                    break;
                }

                sb.deleteCharAt(slat.getKey());
            }
        } catch (Exception ex) {
            throw ExceptionUtils.authorized();
        }

        return sb.toString();
    }

    public static String home(IAuthUser user) {
        return JwtUtils.encode(AuthRoleEnums.HOME, user);
    }

    public static String admin(IAuthUser user) {
        return JwtUtils.encode(AuthRoleEnums.ADMIN, user);
    }

    public static String encode(AuthRoleEnums role, IAuthUser user) {
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(String.valueOf(user.getId()))
                .issuer(JwtConstant.ISSUER)
                .expirationTime(new Date(System.currentTimeMillis() + JwtConstant.DURATION))
                .claim("role", role.getCode())
                .claim("code", user.getCode())
                .claim("name", user.getName())
                .claim("avatar", user.getAvatar())
                .build();

        JWSHeader jwsHeader = new JWSHeader.Builder(JWSAlgorithm.HS256)
                .type(JOSEObjectType.JWT)
                .build();

        SignedJWT signedJWT = new SignedJWT(jwsHeader, claimsSet);

        try {
            signedJWT.sign(new MACSigner(JwtConstant.SECRET));
        } catch (JOSEException ex) {
            throw new RuntimeException(ex);
        }

        return JwtUtils.addSalt(JwtConstant.PREFIX + signedJWT.serialize() + JwtConstant.SUFFIX);
    }

    public static AuthUser decode(String token) {
        String noSaltToken = JwtUtils.cleanSalt(token);

        String noPrefixToken = noSaltToken.substring(JwtConstant.PREFIX.length());

        String noSuffixToken = noPrefixToken.substring(0, noPrefixToken.length() - JwtConstant.SUFFIX.length());

        JWTClaimsSet claimsSet;

        try {
            claimsSet = SignedJWT.parse(noSuffixToken).getJWTClaimsSet();

            // 验证JWT的完整性
            JWSVerifier verifier = new MACVerifier(JwtConstant.SECRET);

            if (!SignedJWT.parse(noSuffixToken).verify(verifier)) {
                throw ExceptionUtils.authorized();
            }
        } catch (ParseException | JOSEException ex) {
            throw ExceptionUtils.authorized();
        }

        // 检查JWT是否已过期
        if (claimsSet.getExpirationTime().before(new Date())) {
            throw ExceptionUtils.authorized();
        }

        AuthUser authUser = new AuthUser();

        authUser.setId(NumberUtils.longOf(claimsSet.getSubject()));
        authUser.setRole((String) claimsSet.getClaim("role"));
        authUser.setCode((String) claimsSet.getClaim("code"));
        authUser.setName((String) claimsSet.getClaim("name"));

        return authUser;
    }

}
