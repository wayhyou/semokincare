package com.semokincare.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.semokincare.model.entity.AppUser;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class JwtUtil {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    @Value("${app.jwt.jwt-secret}")
    private String jwtSecret;
    @Value("${app.jwt.app-name}")
    private String appName;
    @Value("${app.jwt.expired}")
    private long jwtExpired;

    // Create Algorithm
    private Algorithm algorithm;

    @PostConstruct
    private void initAlgorithm() {
        this.algorithm = Algorithm.HMAC256(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(AppUser appUser) {
        logger.info("Generating token for user: {}", appUser.getUsername());

        String token = JWT.create()
                .withIssuer(appName)
                .withSubject(appUser.getId())
                .withExpiresAt(Date.from(Instant.now().plusSeconds(jwtExpired)))
                .withIssuedAt(Date.from(Instant.now()))
                .withClaim("role", appUser.getRole())
                .sign(algorithm);

        logger.info("Token generated successfully for user: {}", appUser.getUsername());
        return token;
    }

    public boolean verifyToken(String token) {
        logger.info("Verifying token");

        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);

        boolean isValid = jwt.getIssuer().equals(appName);
        logger.info("Token verification result: {}", isValid);
        return isValid;
    }

    public Map<String, String> getUserInfoByToken(String token) {
        logger.info("Extracting user info from token");

        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);

        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("userId", jwt.getSubject());
        userInfo.put("role", jwt.getClaim("role").asString());

        logger.info("User info extracted: {}", userInfo);
        return userInfo;
    }
}
