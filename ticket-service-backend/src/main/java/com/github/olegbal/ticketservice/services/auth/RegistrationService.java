package com.github.olegbal.ticketservice.services.auth;

import com.github.olegbal.ticketservice.data.auth.RegistrationDto;
import com.github.olegbal.ticketservice.entities.User;

public interface RegistrationService {

    User registerUser(RegistrationDto registrationDto);

    boolean isRegistrationPossible(RegistrationDto registrationDto);
}
