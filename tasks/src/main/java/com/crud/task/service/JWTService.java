package com.crud.task.service;

import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.crud.task.exception.InvalidJWTException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

/**
 * JWTService which is responsible for Generation and validation of token
 */
@Slf4j
@Service
public class JWTService {

    private static final long JWT_EXPIRY_DURATION = (60 * 60) * 1000;

    @Value("${jwt.secret}")
    private String secretKey;

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRY_DURATION))
                   .signWith(
                           SignatureAlgorithm.HS512, secretKey).compact();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String userName = getUserName(token);
        return isTokenNotExpired(token) && userName.equals(userDetails.getUsername());
    }

    private boolean isTokenNotExpired(String token) {
        try {
            return getClaim(token, Claims::getExpiration).after(new Date());
        } catch (ExpiredJwtException e) {
            log.error("Provided JWT Token is expired [{}]", e.getMessage());
            return false;
        }
    }

    public String getUserName(String token) throws InvalidJWTException {
        if (isTokenNotExpired(token)) {
            return getClaim(token, Claims::getSubject);
        }
        throw new InvalidJWTException("Provided JWT Token is expired");
    }

    private <T> T getClaim(String token, Function<Claims, T> resolver) {
        Claims claims = getAllClaims(token);
        return resolver.apply(claims);
    }

    private Claims getAllClaims(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    public long getTokenExpirationDuration() {
        return JWT_EXPIRY_DURATION;
    }

}
