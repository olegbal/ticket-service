package com.github.olegbal.ticketservice.helpers.cookie;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;

import static com.github.olegbal.ticketservice.enums.ApiVersioningUrlPrefix.V1;

@Component
public class AuthCookieHelper implements CookieHelper {

    private static final String AUTH_COOKIE_PATH = V1;

    //TODO ADD GETTIng VALUE FROM PREPERTIES FILE.
    private static final String AUTH_COOKIE_NAME = "authToken";

    @Override
    public Cookie createCookie(String value) {
        Cookie cookie = new Cookie(getCookieName(), value);
        cookie.setPath(AUTH_COOKIE_PATH);
        return cookie;
    }

    @Override
    public String getCookieName() {
        return AUTH_COOKIE_NAME;
    }
}

