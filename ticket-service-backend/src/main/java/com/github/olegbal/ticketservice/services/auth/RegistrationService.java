package com.github.olegbal.ticketservice.services.auth;

import com.github.olegbal.ticketservice.data.auth.RegistrationDto;
import com.github.olegbal.ticketservice.data.auth.UserDto;

import javax.servlet.http.HttpServletResponse;

public interface RegistrationService {

    UserDto registerUser(RegistrationDto registrationDto, HttpServletResponse response);

    boolean isRegistrationPossible(RegistrationDto registrationDto);
}
