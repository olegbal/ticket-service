package com.github.olegbal.ticketservice.filters;

import com.github.olegbal.ticketservice.security.TokenAuthenticationService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.github.olegbal.ticketservice.enums.ApiVersioningUrlPrefix.V1;

@Component
public class JWTAuthenticationFilter extends GenericFilterBean {

    private final TokenAuthenticationService authenticationService;

    @Autowired
    public JWTAuthenticationFilter(TokenAuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        boolean isLoginPage = httpRequest.getRequestURL().toString().contains(V1 + "/login");
        Authentication authentication = null;
        try {
            authentication = authenticationService.getAuthentication(httpRequest);
            endFiltering(filterChain, authentication, request, response);
        } catch (ExpiredJwtException ex) {
            if (!isLoginPage) {
                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token expired, please log in again");
            } else {
                endFiltering(filterChain, authentication, request, response);
            }
        }
    }

    private void endFiltering(FilterChain filterChain, Authentication authentication, ServletRequest request,
                              ServletResponse response) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}