package com.github.olegbal.ticketservice.converters;

import com.github.olegbal.ticketservice.data.auth.RegistrationDto;
import com.github.olegbal.ticketservice.entities.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RegistrationDtoToUserEntityConverter implements Converter<RegistrationDto, User> {
    @Override
    public User convert(RegistrationDto source) {
        User convertedUser = new User();
        convertedUser.setLogin(source.getLogin());
        convertedUser.setPassword(source.getPassword());
        convertedUser.setFirstName(source.getFirstName());
        convertedUser.setLastName(source.getLastName());
        convertedUser.setOrganization(source.getOrganization());
        convertedUser.setPhoneNumber(source.getPhoneNumber());
        convertedUser.setEmail(source.getEmail());

        return convertedUser;
    }
}
