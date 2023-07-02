package com.arxivj.weekend_server.global.util;

import com.arxivj.weekend_server.domain.member.domain.Member;
import com.arxivj.weekend_server.domain.model.Email;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.*;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Component
public class JwtUtil {

    @Value("${jwt.secret-key}")
    public String secretKey;
    private static String key; // class 로딩시 key=null 초기화 > JwtUtil instance 생성 후 @Value 주입 > init으로 key할당

    @PostConstruct
    public void init() {
        String encodedBytes = secretToBase64(this.secretKey);
        key = String.valueOf(base64ToKey(encodedBytes));
        log.info("init, key = {}", key);
    }

    public String secretToBase64(String secretKey) {
        byte[] secretBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        byte[] encodedBytes = Base64.getEncoder().encode(secretBytes);
        if (encodedBytes.length < 64) {
            throw new IllegalArgumentException("secretKey must be at least 64 bytes for HS512 algorithm");
        }
        log.info("secretToBase64,secretKey = {}", secretKey);
        return new String(encodedBytes);
    }

    private SecretKeySpec base64ToKey(String encodedBytes) {
        byte[] keyBytes = Base64.getDecoder().decode(encodedBytes);
        log.info("base64ToKey, encodedBytes = {}", encodedBytes);
        return new SecretKeySpec(keyBytes, "HmacSHA256");
    }


    public static String createAccessToken(Member member, Email email) {
        log.info("# Check access to 'createAccessToken' logic ");
        return JWT.create()
                .withSubject(member.getName().getFullName())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .withClaim("email", email.getValue())
                .sign(Algorithm.HMAC512(key));
    }

    public static String createRefreshToken(Member member, Email email) {
        log.info("# Check access to 'createRefreshToken' logic ");
        return JWT.create()
                .withSubject(member.getEmail().toString())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME_REFRESH))
                .withClaim("email", email.getValue())
                .withClaim("secret", "actually no big deal")
                .sign(Algorithm.HMAC512(key));
    }

    public static void accessTokenSetHeader(HttpServletResponse response, String accessToken) {
        response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + accessToken);
    }

    public static Email getEmailFromAuthorization(String bearerToken){
        log.info("bearerToken = {}", bearerToken);
        String token = bearerToken.replace("Bearer ", "");
        log.info("token = {}", token);
        DecodedJWT decodedJWT = JWT.decode(token);
        String emailValue = decodedJWT.getClaim("email").asString();
        return Email.of(emailValue);
    }



    public static void validateAccessToken(String accessToken) {
        try {
            JWT.require(Algorithm.HMAC512(key))
                    .withIssuer("auth0")
                    .build()
                    .verify(accessToken);  // 필수! accessToken을 검증
        } catch (TokenExpiredException e) {
            // JWT 토큰의 유효기간이 만료되었을 경우 처리할 코드
            throw new RuntimeException("Access token has expired.", e);
        } catch (SignatureVerificationException e) {
            // JWT 토큰의 서명 검증에 실패했을 경우 처리할 코드
            throw new RuntimeException("Signature verification failed.", e);
        } catch (AlgorithmMismatchException e) {
            // JWT 토큰에 지정된 알고리즘과 검증에 사용한 알고리즘이 일치하지 않을 경우 처리할 코드
            throw new RuntimeException("Algorithm does not match with the token's algorithm.", e);
        } catch (InvalidClaimException e) {
            // JWT 토큰의 클레임이 기대하는 값과 일치하지 않을 경우 처리할 코드
            throw new RuntimeException("Claim values do not match with the expected values.", e);
        } catch (JWTVerificationException e) {
            // 기타 JWT 토큰 검증과 관련된 예외를 처리하는 코드
            throw new RuntimeException("Failed to verify JWT token.", e);
        }
    }

}
