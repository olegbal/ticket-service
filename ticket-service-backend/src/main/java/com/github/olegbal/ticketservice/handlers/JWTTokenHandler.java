package com.github.olegbal.ticketservice.handlers;

import com.github.olegbal.ticketservice.configurations.security.SecurityProperties;
import com.github.olegbal.ticketservice.entities.User;
import com.github.olegbal.ticketservice.services.user.UserInfoService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JWTTokenHandler implements TokenHandler {

    private final SecurityProperties securityProperties;
    private final UserInfoService userInfoService;

    @Override
    public User parseUserFromToken(String token) {
        try {
            String username = Jwts.parser()
                    .setSigningKey(securityProperties.getSecret())
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
        Date expiration = new Date(now.getTime() + TimeUnit.HOURS.toMillis(2L));
        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setSubject(user.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512, securityProperties.getSecret())
                .compact();
    }
}
