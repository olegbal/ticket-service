package com.github.olegbal.ticketservice.converters;

import com.github.olegbal.ticketservice.data.auth.LoginAndPasswordDto;
import com.github.olegbal.ticketservice.entities.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LoginDtoToUserEntityConverter implements Converter<LoginAndPasswordDto, User> {
    @Override
    public User convert(LoginAndPasswordDto source) {

        User convertedUser = new User();
        convertedUser.setLogin(source.getLogin());
        convertedUser.setPassword(source.getPassword());
        return convertedUser;
    }
}
