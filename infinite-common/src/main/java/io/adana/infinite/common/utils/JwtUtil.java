package io.adana.infinite.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author simon
 * @date 2020/8/6 14:06
 * @description jwt 生成token
 */
@ConfigurationProperties(prefix = "infinite.jwt")
@Component
@Data
@Slf4j
public class JwtUtil {

    /**
     * secret key
     */
    private String secret;
    /**
     * expire date
     */
    private Long expiration;
    /**
     * token header.
     */
    private String header;

    /**
     * 根据用户信息生成token
     *
     * @param userDetails user information
     * @return token
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>(8);
        claims.put("sub", userDetails.getUsername());
        claims.put("created", new Date());
        return generateToken(claims);
    }

    /**
     * 根据负责生成JWT的token
     *
     * @param claims map
     * @return token
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 从token中获取JWT中的负载
     *
     * @param token token
     * @return map
     */
    public Claims parseToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception ex) {
            log.info("jwt error:{}", token);
            return null;
        }
    }

    /**
     * 验证jwt是否合法
     *
     * @param token       token口令
     * @param userDetails 用户详情
     * @return true/false
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    /**
     * token是否过期
     *
     * @param  token token口令
     * @return true：过期
     */
    public boolean isTokenExpired(String token) {
        Date expiration = getExpirationFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * token续期
     *
     * @return 生成过期日期
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * 从Token中获取用户名
     *
     * @param token token口令
     * @return 用户名
     */
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    /**
     * 从Token中获取过期时间
     *
     * @param token token
     * @return 过期时间
     */
    public Date getExpirationFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    /**
     * 分解Token，获取需要的部分
     */
    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {

        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);
    }
}
