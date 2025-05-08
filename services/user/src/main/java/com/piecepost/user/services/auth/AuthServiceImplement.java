package com.piecepost.user.services.auth;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

import com.piecepost.grpc.cache.RedisServiceGrpc;
import com.piecepost.grpc.cache.CacheResponse;
import com.piecepost.grpc.cache.CacheSetRequest;
import com.piecepost.keys.auth.tokens.AccessTokenClaims;
import com.piecepost.keys.auth.tokens.TokenType;
import com.piecepost.user.https.responses.auth.LoginResponse;

import com.piecepost.user.models.entities.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {
    @Value("${jwt.secret-key}")
    private String secretKey;
    private final int accessTokenExpireIns = 24 * 60 * 60;
    private final int refreshTokenExpireIns = 30 * 24 * 60 * 60;
    private final RedisServiceGrpc.RedisServiceBlockingStub cacheStub;

   
    @Override
    public void sendVerificationCode(String email) throws MessagingException {
        String randomCode = String.format(email, null);
        CacheSetRequest setRequest = CacheSetRequest.newBuilder().setKey(randomCode).setValue("1").build();
        CacheResponse response = cacheStub.set(setRequest);
    }
    // @Override
    // public LoginResponse register(){

    // }

    protected Map<String,Object> generateToken(String deviceId,User user){
        String userId = String.valueOf(user.getId());
        SecretKey secretKeyJwt = Keys.hmacShaKeyFor(secretKey.getBytes());
        String accessToken = Jwts.builder().claim(TokenType.STRING_KEY, TokenType.ACCESS.getValue())
        .claim(AccessTokenClaims.USER_ID_STRING_KEY, userId)
        .claim(AccessTokenClaims.USER_ROLE_STRING_KEY, user.getRole())
        .claim(AccessTokenClaims.DEVICE_ID_STRING_KEY, deviceId)
        .issuedAt(Date.from(Instant.now())).expiration(Date.from(Instant.now().plusSeconds(accessTokenExpireIns))).signWith(secretKeyJwt).compact();
        String refreshToken = Jwts.builder().claim(TokenType.STRING_KEY, TokenType.REFRESH.getValue()).issuedAt(Date.from(Instant.now())).expiration(Date.from(Instant.now().plusSeconds(accessTokenExpireIns))).signWith(secretKeyJwt).compact();
        Map<String, Object> data = new HashMap<>();
        data.put(TokenType.STRING_KEY, TokenType.BEARER_STRING_KEY);
        data.put(TokenType.ACCESS.getValue(), accessToken);
        data.put(TokenType.REFRESH.getValue(), refreshToken);
        return data;
    }


}
