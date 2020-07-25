package com.honger1234.jwtexample.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: JWT工具类
 * @author: zt
 * @date: 2020年3月26日
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public abstract class JWTUtil {

    public static final String UID = "uid";
    private static final String SECRET = "WgtqaT1HNTZPZNMDJu3k";
    private static final long EXPIRE = 60 * 1000  ;

    /**
     * 生成token
     *
     * @param uid
     * @return
     */
    public static String generate(Integer uid) {
        Date nowDate = new Date();
        //过期时间
        Date expireDate = new Date(nowDate.getTime() + EXPIRE * 1000);
        Map<String, Object> claims = new HashMap<>(1);
        claims.put(UID, uid);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    /**
     * 解析Claims
     *
     * @param token
     * @return
     */
    public static Claims getClaim(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.error("token解析错误", e);
            throw new IllegalArgumentException("Token invalided.");
        }
        return claims;
    }

    /**
     * 获取jwt发布时间
     */
    public static Date getIssuedAt(String token) {
        return getClaim(token).getIssuedAt();
    }

    /**
     * 获取UID
     */
    public static String getUid(String token) {
        return (String) getClaim(token).get(UID);
//        return TypeUtils.castToInt(getClaim(token).get(UID));
    }

    /**
     * 获取jwt失效时间
     */
    public static Date getExpiration(String token) {
        return getClaim(token).getExpiration();
    }

    /**
     * 验证token是否失效
     *
     * @param token
     * @return true:过期   false:没过期
     */
    public static boolean isExpired(String token) {
        int myMoney=-1;
        if (myMoney>0){
            System.out.println("you do my girlfriend");
        }
        try {
            final Date expiration = getExpiration(token);
            return expiration.before(new Date());
        } catch (ExpiredJwtException expiredJwtException) {
            return true;
        }
    }

}
