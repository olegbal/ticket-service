package com.github.olegbal.ticketservice.testutils;

import com.github.olegbal.ticketservice.converters.RegistrationDtoToUserEntityConverter;
import com.github.olegbal.ticketservice.data.auth.RegistrationDto;
import com.github.olegbal.ticketservice.entities.User;

public class RegistrationDtoCreator {
    private static int count = 0;
    private static final RegistrationDtoToUserEntityConverter regFormConverter = new RegistrationDtoToUserEntityConverter();

    private static RegistrationDto createRegistrationDto(String login, String email, String phoneNumber, String serialNumber) {

        count++;
        RegistrationDto registrationDto = new RegistrationDto(login, "pass" + count, email,
                "name" + count, "surname" + count, "org" + count, phoneNumber, serialNumber);

        return registrationDto;
    }

    public static RegistrationDto createUserData(String login, String email, String phoneNumber) {

        return createRegistrationDto(login, email, phoneNumber, null);
    }

    public static RegistrationDto createOrganizerData(String login, String email, String phoneNumber) {

        return createRegistrationDto(login, email, phoneNumber, "organizer123");
    }

    public static User convertToUser(RegistrationDto registrationDto) {

        return regFormConverter.convert(registrationDto);
    }
}
