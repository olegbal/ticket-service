package com.github.olegbal.ticketservice.services.auth;

import com.github.olegbal.ticketservice.data.auth.LoginAndPasswordDto;
import com.github.olegbal.ticketservice.data.auth.UserDto;

import javax.servlet.http.HttpServletResponse;

public interface LoginService {

    UserDto logIn(LoginAndPasswordDto loginAndPasswordDto, HttpServletResponse response);
}
