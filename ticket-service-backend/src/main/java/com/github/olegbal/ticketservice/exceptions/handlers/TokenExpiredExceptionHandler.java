package com.github.olegbal.ticketservice.exceptions.handlers;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages = {"com.github.olegbal.ticketservice"})
public class TokenExpiredExceptionHandler {

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity handleExpiredJwtException() {
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

}
