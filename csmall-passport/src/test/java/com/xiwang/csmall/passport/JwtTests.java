package com.xiwang.csmall.passport;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class JwtTests {

    String secretKey = "kns439a}fdLK34jsmfd{MF5-8DJSsLKhJNFDSjn";

    @Test
    public void testTokenAnalysis() {
        String jwt = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6OTUyNywiZXhwIjoxNjY1NTY4ODMyLCJlbWFpbCI6ImxpdWNhbmdzb25nQDE2My5jb20iLCJ1c2VybmFtZSI6ImxpdWNhbmdzb25nIn0.BrqC9ncYCblpTvayrZg39WLFMsYgmeukBf3b-GVBlzI";
        System.out.println(jwt);
        Claims claim = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody();
        System.out.println("id="+claim.get("id", Integer.class));
        System.out.println("username="+claim.get("username", String.class));
        System.out.println("password="+claim.get("email", String.class));
    }

    @Test
    public void testGenerate() {

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 9527);
        claims.put("username", "liucangsong");
        claims.put("email", "liucangsong@163.com");

        Date expirationDate = new Date(System.currentTimeMillis() + 10 * 60 * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        System.out.println("过期时间：" + sdf.format(expirationDate));

        String jwt = Jwts.builder()
                // Header
                .setHeaderParam("alg", "HS256")
                .setHeaderParam("typ", "JWT")
                // Payload
                .setClaims(claims)
                .setExpiration(expirationDate)
                // Signature
                .signWith(SignatureAlgorithm.HS256, secretKey)
                // 整合
                .compact();
        System.out.println(jwt);
    }

}
