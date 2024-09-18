package com.licnosoft.linkedin.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import com.licnosoft.linkedin.domain.enumeration.UserRole;
import com.licnosoft.linkedin.model.auth.UserInfoDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtUtil {

//    @Value("${app.auth-disabled:false}")
//    private boolean authDisabled;

    @Value("${jwt.expiresSec}")
    private long expiresSec;

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(UserInfoDTO userInfo) {
        return createJwt(userInfo, expiresSec);
    }

    private String createJwt(UserInfoDTO userInfo, long expiration) {
       return JWT.create()
               .withSubject(userInfo.getId().toString())
               .withClaim("email", userInfo.getEmail())
               .withClaim("firstName", userInfo.getFirstName())
               .withClaim("lastName", userInfo.getLastName())
               .withClaim("userRole", userInfo.getUserRole().toString())
               .withClaim("password", userInfo.getPassword())
               .withExpiresAt(new Date(System.currentTimeMillis() + expiration * 1000))
               .withIssuedAt(new Date(System.currentTimeMillis()))
               .sign(getAlgorithm());
    }

    private Algorithm getAlgorithm() {
        return Algorithm.HMAC512(secret);
    }

    public UserInfoDTO decodeJwtToUserInfo(String jwt) {
        DecodedJWT decodedJWT = JWT.decode(jwt);

        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setId(Long.valueOf(decodedJWT.getSubject()));
        userInfoDTO.setEmail(decodedJWT.getClaim("email").asString());
        userInfoDTO.setFirstName(decodedJWT.getClaim("firstName").asString());
        userInfoDTO.setLastName(decodedJWT.getClaim("lastName").asString());
        userInfoDTO.setUserRole(UserRole.valueOf(decodedJWT.getClaim("userRole").asString()));
        userInfoDTO.setPassword(decodedJWT.getClaim("password").asString());

        return userInfoDTO;
    }

//    public TokenServiceType getTokenType(String jwt) {
//        DecodedJWT decodedJWT = JWT.decode(jwt);
//        return TokenServiceType.valueOf(decodedJWT.getClaim("type").asString());
//    }
}
