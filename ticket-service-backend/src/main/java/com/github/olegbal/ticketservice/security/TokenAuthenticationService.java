package com.github.olegbal.ticketservice.security;

import com.github.olegbal.ticketservice.entities.User;
import com.github.olegbal.ticketservice.handlers.TokenHandler;
import com.github.olegbal.ticketservice.helpers.cookie.AuthCookieHelper;
import com.github.olegbal.ticketservice.services.cookie.DefaultRequestResponseCookieService;
import com.github.olegbal.ticketservice.services.user.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TokenAuthenticationService {

    private final UserInfoService userService;
    private final TokenHandler tokenHandler;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthCookieHelper authCookieService;
    private final DefaultRequestResponseCookieService reqResCookieService;

    public boolean checkLogin(HttpServletResponse response, User authCheckDto) {

        User user = userService.loadUserByUsername(authCheckDto.getLogin());
        if (user != null) {
            if (bCryptPasswordEncoder.matches(authCheckDto.getPassword(), user.getPassword())) {
                Cookie authCookie = authCookieService.createCookie(tokenHandler.createTokenForUser(user));
                reqResCookieService.putCookieToResponse(authCookie, response);

                return true;
            }
        }
        return false;
    }

    public Authentication getAuthentication(HttpServletRequest request) {

        Cookie authCookie = reqResCookieService.getCookieFromRequest(authCookieService.getCookieName(), request);

        if (authCookie == null) {
            return null;
        }

        String token = authCookie.getValue();
        final User user = tokenHandler.parseUserFromToken(token);
        if (user != null) {
            return new UserAuthentication(user);
        }
        return null;
    }
}