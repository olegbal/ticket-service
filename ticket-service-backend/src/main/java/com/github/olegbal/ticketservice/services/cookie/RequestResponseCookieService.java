package com.github.olegbal.ticketservice.services.cookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface RequestResponseCookieService {

    void putCookieToResponse(Cookie cookie, HttpServletResponse response);

    Cookie getCookieFromRequest(String cookieName, HttpServletRequest request);
}
