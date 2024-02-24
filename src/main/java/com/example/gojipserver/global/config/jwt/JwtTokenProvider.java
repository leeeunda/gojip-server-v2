package com.example.gojipserver.global.config.jwt;


import com.example.gojipserver.domain.oauth2.entity.UserPrincipal;
import com.example.gojipserver.domain.oauth2.service.CustomUserDetailsService;
import com.example.gojipserver.global.config.redis.entity.RefreshToken;
import com.example.gojipserver.global.config.redis.repository.RefreshTokenRepository;
import com.example.gojipserver.global.entity.ExpireTime;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private static final String AUTH_KEY = "AUTHORITIES";
    private static final String BEARER_TYPE = "Bearer";
    private static final String TYPE_ACCESS = "access";
    private static final String TYPE_REFRESH = "refresh";

    @Value("${jwt.access.header}")
    private String accessHeader;

    @Value("${jwt.refresh.header}")
    private String refreshHeader;

    @Value("${jwt.secret}")
    private String secretKey;
    private final CustomUserDetailsService userDetailsService;
    private final RefreshTokenRepository refreshTokenRepository;

    // 토큰 생성
    public String createAccessToken(String refreshToken,Collection<? extends GrantedAuthority> authorities) {
        RefreshToken findRefreshToken = refreshTokenRepository.findById(refreshToken).orElseThrow(() -> new RuntimeException("Refresh Token not found"));
        String email = findRefreshToken.getEmail();
        Claims claims = Jwts.claims().setSubject(email); // JWT payload 에 저장되는 정보단위
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims) // 정보 저장
                .claim(AUTH_KEY, authorities)
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + ExpireTime.ACCESS_TOKEN_EXPIRE_TIME))
                .signWith(SignatureAlgorithm.HS256, secretKey)  // 암호화 알고리즘과, secret 값
                .compact();
    }

    public String createRefreshToken(String email, Collection<? extends GrantedAuthority> authorities){
        Claims claims = Jwts.claims().setSubject(email);
        Date now = new Date();
        String refreshToken = Jwts.builder()
                .setClaims(claims)
                .claim(AUTH_KEY, authorities)
                .setIssuedAt(now)   //토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + ExpireTime.REFRESH_TOKEN_EXPIRE_TIME)) //토큰 만료 시간 설정
                .signWith(SignatureAlgorithm.HS256,secretKey)
                .compact();

        // 리프레시 토큰 저장
        RefreshToken newRefreshToken = new RefreshToken(refreshToken, email);
        refreshTokenRepository.save(newRefreshToken);
        return refreshToken;
    }

    public void sendAccessAndRefreshToken(HttpServletResponse response, String accessToken, String refreshToken) {
        response.setStatus(HttpServletResponse.SC_OK);

        response.setHeader(accessHeader, "Bearer " + accessToken);
        response.setHeader(refreshHeader, "Bearer " + refreshToken);
        log.info("Access Token, Refresh Token 헤더 설정 완료");
    }

    public Authentication getAuthentication(String token) {
        log.info(this.getUserEmail(token));
        UserPrincipal userPrincipal = (UserPrincipal) userDetailsService.loadUserByUsername(this.getUserEmail(token));
        return new UsernamePasswordAuthenticationToken(userPrincipal, "", userPrincipal.getAuthorities());
    }
    public String getUserEmail(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }



    //토큰 정보를 검증하는 메서드
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e);
        }
        return false;
    }
}
