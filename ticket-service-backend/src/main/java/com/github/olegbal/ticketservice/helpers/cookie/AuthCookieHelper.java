package com.github.olegbal.ticketservice.helpers.cookie;

import com.github.olegbal.ticketservice.configurations.security.SecurityProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;

import static com.github.olegbal.ticketservice.enums.ApiVersioningUrlPrefix.V1;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthCookieHelper implements CookieHelper {

    private static final String AUTH_COOKIE_PATH = V1;

    private final SecurityProperties securityProperties;

    @Override
    public Cookie createCookie(String value) {
        Cookie cookie = new Cookie(getCookieName(), value);
        cookie.setPath(AUTH_COOKIE_PATH);
        return cookie;
    }

    @Override
    public String getCookieName() {
        return securityProperties.getCookieName();
    }
}

