package com.github.olegbal.ticketservice.helpers.cookie;

import javax.servlet.http.Cookie;

public interface CookieHelper {

    Cookie createCookie(String value);

    String getCookieName();
}
