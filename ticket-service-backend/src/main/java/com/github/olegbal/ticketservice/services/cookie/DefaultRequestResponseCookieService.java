package com.github.olegbal.ticketservice.services.cookie;

import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Service
public class DefaultRequestResponseCookieService implements RequestResponseCookieService {
    @Override
    public void putCookieToResponse(Cookie cookie, HttpServletResponse response) {
        response.addCookie(cookie);
    }

    @Override
    public Cookie getCookieFromRequest(String cookieName, HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();

        return Arrays.stream(cookies)
                .filter(x -> x.getName().equals(cookieName))
                .findAny().orElse(new Cookie(cookieName, ""));
    }
}
