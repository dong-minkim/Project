package com.dutmdcjf.backendserver.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider {

    @Value("${jwt.secretKey}")
    private String secretkey;

    public String getHeaderToken(HttpServletRequest request) {
        if (request.getHeader("Authorization") != null) {
            return request.getHeader("Authorization").substring(7);
        }
        return null;
    }

    public String getAccountId(String token) {
        return getClaimsToken(token).get("userId", String.class);
    }

    public String getEmail(String token) {
        return getClaimsToken(token).getSubject();
    }

    public Date getExpiration(String token) {
        return getClaimsToken(token).getExpiration();
    }

    public boolean isValidToken(String token) {
        return getClaimsToken(token).getExpiration().after(new Date());
    }

    private Claims getClaimsToken(String token) {
        Key securityKey = Keys.hmacShaKeyFor(secretkey.getBytes(StandardCharsets.UTF_8));
        return Jwts.parserBuilder()
                .setSigningKey(securityKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
