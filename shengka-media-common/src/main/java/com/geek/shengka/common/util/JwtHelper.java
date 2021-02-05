package com.geek.shengka.common.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.Map;

public class JwtHelper {

    public static Claims parseJWT(String jsonWebToken, String secretKey) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
                    .parseClaimsJws(jsonWebToken).getBody();
            return claims;
        } catch (Exception ex) {
            return null;
        }
    }

    public static String createJWT(Map<String,Object> params, String secretKey, int expiresSecond) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                .setClaims(params)
                .signWith(signatureAlgorithm, signingKey);
        //添加Token过期时间
        if (expiresSecond >= 0) {
            long expMillis = nowMillis + expiresSecond;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp).setNotBefore(now);
        }

        //生成JWT
        return builder.compact();
    }

    public static void main(String[] args) throws InterruptedException {

//        Map<String,Object> params = new HashMap<>();
//        params.put("epId","2000600343454565464");
//
//		String token = JwtHelper.createJWT(params,"",7200000);
//		System.out.println("token: "+token);
//		Claims claims = JwtHelper.parseJWT(token,"");
//        String epId = (String) claims.get("epId");
//		System.out.println("epId: "+epId);
        long nowMillis = System.currentTimeMillis();
        long expMillis = nowMillis + 7200000;
        Date now = new Date(expMillis);
        System.out.println("epId: "+now);

	}
}
