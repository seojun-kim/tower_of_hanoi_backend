package kr.hs.dgsw.towerofhanoi.global.jwt.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class JwtUtils {

    private final byte[] accessSecret;
    private final byte[] refreshSecret;

    private final static Long ACCESS_TOKEN_EXPIRE_COUNT = 1000L * 60 * 30;
    private final static Long REFRESH_TOKEN_EXPIRE_COUNT = 1000L * 60 * 60 * 24 * 7;

    public JwtUtils(@Value("${jwt.secretKey}") String accessSecret, @Value("${jwt.refreshKey}") String refreshSecret) {
        this.accessSecret = accessSecret.getBytes(StandardCharsets.UTF_8);
        this.refreshSecret = refreshSecret.getBytes(StandardCharsets.UTF_8);
    }

    public String createAccessToken(Long id, String username, List<String> roles) {
        return createToken(id, username, roles, ACCESS_TOKEN_EXPIRE_COUNT, accessSecret);
    }

    public String createRefreshToken(Long id, String username, List<String> roles) {
        return createToken(id, username, roles, REFRESH_TOKEN_EXPIRE_COUNT, refreshSecret);
    }

    public Long getUsernameFormToken(String token) {

        token = token.split(" ")[1];
        Claims claims = parseToken(token, accessSecret);

        return Long.valueOf((Integer)claims.get("memberId"));
    }

    public String createToken(Long id, String username, List<String> roles, Long expire, byte[] secretKey) {

        Claims claims = Jwts.claims().setSubject(username);
        claims.put("id", id);
        claims.put("roles", roles);

        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expire))
                .signWith(getSigningKey(secretKey))
                .compact();
    }

    public Long getMemberIdFormToken(String token) {

        token = token.split(" ")[1];
        Claims claims = parseToken(token, accessSecret);

        return Long.valueOf((Integer)claims.get("id"));
    }

    public Claims parseAccessToken(String accessToken) {
        return parseToken(accessToken, accessSecret);
    }

    public Claims parseRefreshToken(String refreshToken) {
        return parseToken(refreshToken, refreshSecret);
    }

    public Claims parseToken(String token, byte[] secretKey) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public static Key getSigningKey(byte[] secretKey) {
        return Keys.hmacShaKeyFor(secretKey);
    }
}
