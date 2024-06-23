package com.licnosoft.linkedin.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import com.licnosoft.linkedin.model.auth.UserInfoDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtUtil {

    @Value("${app.auth-disabled:false}")
    private boolean authDisabled;

    @Value("${jwt.expiresSec}")
    private long expiresSec;

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(UserInfoDTO userInfo) {
        return createJwt(userInfo, expiresSec);
    }

    private String createJwt(UserInfoDTO userInfo, long expiration) {
       return JWT.create()
               .withSubject(userInfo.getEmail())
               .withClaim("firstname", userInfo.getFirstName())
               .withClaim("role", userInfo.getRole())
               .withClaim("lastname", userInfo.getLastName())
               .withExpiresAt(new Date(System.currentTimeMillis() + expiration * 1000))
               .withIssuedAt(new Date(System.currentTimeMillis()))
               .sign(getAlgorithm());
    }

    private Algorithm getAlgorithm() {
        return Algorithm.HMAC512(secret);
    }

    public UserInfoDTO decodeJwtToUserInfo(String jwt) {
        DecodedJWT decodedJWT;
        decodedJWT = JWT.decode(jwt);

        return new UserInfoDTO(decodedJWT.getSubject(),
                decodedJWT.getClaim("password").asString(),decodedJWT.getClaim("firstname").asString(), decodedJWT.getClaim("lastname").asString(), decodedJWT.getClaim("role").asString());
    }

//    public TokenServiceType getTokenType(String jwt) {
//        DecodedJWT decodedJWT = JWT.decode(jwt);
//        return TokenServiceType.valueOf(decodedJWT.getClaim("type").asString());
//    }
}
