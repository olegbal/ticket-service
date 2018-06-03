package com.github.olegbal.ticketservice.services.auth;

import com.github.olegbal.ticketservice.data.auth.RegistrationDto;
import com.github.olegbal.ticketservice.data.auth.UserDto;
import com.github.olegbal.ticketservice.entities.User;
import com.github.olegbal.ticketservice.factories.SerialNumberRolesFactory;
import com.github.olegbal.ticketservice.services.user.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultRegistrationService implements RegistrationService {

    private final UserInfoService userInfoService;
    private final ConversionService conversionService;
    private final SerialNumberRolesFactory rolesFactory;

    @Override
    public UserDto registerUser(RegistrationDto registrationDto) {

        if (!isRegistrationPossible(registrationDto)) {
            return null;
        }

        User user = conversionService.convert(registrationDto, User.class);

        user.setRoles(rolesFactory.getRolesList(registrationDto.getSecretCode()));

        if (userInfoService.createUser(user) == null) {
            return null;
        }

        return conversionService.convert(user, UserDto.class);

    }

    @Override
    public boolean isRegistrationPossible(RegistrationDto registrationDto) {

        return !userInfoService.isUserExists(registrationDto.getLogin(), registrationDto.getEmail());
    }


}
