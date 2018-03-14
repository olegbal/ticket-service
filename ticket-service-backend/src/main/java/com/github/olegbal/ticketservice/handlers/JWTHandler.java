package com.github.olegbal.ticketservice.handlers;

import com.github.olegbal.ticketservice.entities.User;
import com.github.olegbal.ticketservice.services.user.UserInfoService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class JWTHandler implements TokenHandler {

    //TODO Get it from properties
    private final String TOKEN_SECRET = "MY_SECRET_TSSS";
    private UserInfoService userInfoService;

    @Autowired
    public JWTHandler(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @Override
    public User parseUserFromToken(String token) {
        try {
            String username = Jwts.parser()
                    .setSigningKey(TOKEN_SECRET)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
            return userInfoService.loadUserByUsername(username);
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }

    @Override
    public String createTokenForUser(User user) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + TimeUnit.SECONDS.toMillis(1L));
        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setSubject(user.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512, TOKEN_SECRET)
                .compact();
    }
}
