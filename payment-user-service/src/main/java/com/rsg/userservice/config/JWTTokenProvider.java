package com.rsg.userservice.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;


@Component
public class JWTTokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long validityInMilliseconds;


    public String createToken(String username) {
        Date date = new Date();
        Date validity = new Date(date.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setSubject(username)
                .setExpiration(validity)
                .setIssuedAt(date)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }


    public String getUserName(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }


    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }

    }


}
