package com.github.olegbal.ticketservice.converters;

import com.github.olegbal.ticketservice.data.auth.UserDto;
import com.github.olegbal.ticketservice.entities.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserEntityToDtoConverter implements Converter<User, UserDto> {
    @Override
    public UserDto convert(User source) {

        UserDto userDto = new UserDto();
        userDto.setId(source.getId());
        userDto.setEmail(source.getEmail());
        userDto.setFirstName(source.getFirstName());
        userDto.setLastName(source.getLastName());
        userDto.setLogin(source.getLogin());
        userDto.setPassword(source.getPassword());
        userDto.setOrganization(source.getOrganization());
        userDto.setPhoneNumber(source.getPhoneNumber());
        userDto.setRoles(source.getRoles());

        return userDto;
    }
}
