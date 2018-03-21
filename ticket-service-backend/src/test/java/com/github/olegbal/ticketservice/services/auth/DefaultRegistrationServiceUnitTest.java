package com.github.olegbal.ticketservice.services.auth;

import com.github.olegbal.ticketservice.testutils.RegistrationDtoCreator;
import com.github.olegbal.ticketservice.converters.RegistrationDtoToUserEntityConverter;
import com.github.olegbal.ticketservice.converters.UserEntityToDtoConverter;
import com.github.olegbal.ticketservice.data.auth.RegistrationDto;
import com.github.olegbal.ticketservice.data.auth.UserDto;
import com.github.olegbal.ticketservice.entities.Role;
import com.github.olegbal.ticketservice.entities.User;
import com.github.olegbal.ticketservice.enums.Roles;
import com.github.olegbal.ticketservice.factories.SerialNumberRolesFactory;
import com.github.olegbal.ticketservice.services.user.UserInfoService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.GenericConversionService;

import java.util.Collections;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;

public class DefaultRegistrationServiceUnitTest {

    private ConversionService conversionService;
    private UserInfoService userInfoService;
    private SerialNumberRolesFactory rolesFactory;
    private RegistrationService registrationService;

    @Before
    public void setUp() {
        userInfoService = Mockito.mock(UserInfoService.class);

        setupConverters();

        rolesFactory = Mockito.mock(SerialNumberRolesFactory.class);
        registrationService = new DefaultRegistrationService(userInfoService, conversionService, rolesFactory);

        when(userInfoService.isUserExists("user1")).thenReturn(false);
        when(userInfoService.isUserExists("noSuchUser")).thenReturn(true);
        when(userInfoService.isUserExists("organizer1")).thenReturn(false);

        when(userInfoService.createUser(anyObject())).thenReturn(new User());
        when(userInfoService.createUser(null)).thenReturn(null);

        when(rolesFactory.getRolesList("organizer123"))
                .thenReturn(Collections.singleton(new Role(Roles.ORGANIZER.roleId(), Roles.ORGANIZER.roleName())));
        when(rolesFactory.getRolesList(null))
                .thenReturn(Collections.singleton(new Role(Roles.USER.roleId(), Roles.USER.roleName())));


    }

    //Setting up converters for conversionService
    public void setupConverters() {
        conversionService = new GenericConversionService();
        GenericConversionService genericConversionService = (GenericConversionService) conversionService;
        genericConversionService.addConverter(new RegistrationDtoToUserEntityConverter());
        genericConversionService.addConverter(new UserEntityToDtoConverter());
    }

    @Test
    public void registerUser() {
        RegistrationDto userRegData = RegistrationDtoCreator.createUserData("user1", "user1@gmail.com", "123456");
        RegistrationDto orgRegData = RegistrationDtoCreator.createOrganizerData("organizer1", "organizer1@gmail.com", "323345");
        RegistrationDto emptyData = RegistrationDtoCreator.createUserData("noSuchUser", "noMail@gmail.com", "000000");

        UserDto notFoundUser = registrationService.registerUser(emptyData);
        UserDto registeredUser = registrationService.registerUser(userRegData);
        UserDto registeredOrganizer = registrationService.registerUser(orgRegData);

        assertThat(notFoundUser).isNull();

        assertThat(registeredUser).isNotNull();
        assertThat(registeredOrganizer).isNotNull();

        assertThat(registeredUser.getRoles().stream()
                .filter(x -> x.getRoleName().equals(Roles.USER.roleName())).count() == 1);
        assertThat(registeredOrganizer.getRoles().stream()
                .filter(x -> x.getRoleName().equals(Roles.ORGANIZER.roleName())).count() == 1);

    }
}