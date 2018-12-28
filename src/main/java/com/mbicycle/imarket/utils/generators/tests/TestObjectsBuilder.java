package com.mbicycle.imarket.utils.generators.tests;

import com.mbicycle.imarket.beans.entities.Profile;
import com.mbicycle.imarket.beans.entities.User;
import com.mbicycle.imarket.dto.ProfileDTO;
import com.mbicycle.imarket.dto.UserDTO;

import java.util.Arrays;

import static com.mbicycle.imarket.utils.enums.RoleType.ADMIN;
import static com.mbicycle.imarket.utils.enums.RoleType.CUSTOMER;

public class TestObjectsBuilder {

    public static UserDTO createUserDTO(String login, String password) {
        return new UserDTO(login, password
                , Arrays.asList(CUSTOMER.name(), ADMIN.name()));
    }

    public static User createUser(String login, String password) {
        return new User(login, password
                , Arrays.asList(CUSTOMER.getRole(), ADMIN.getRole()));
    }

    public static User createOnlyUser(String login, String password) {
        return new User(login, password
                , Arrays.asList(CUSTOMER.getRole()));
    }

    public static Profile createProfile(String name, User user) {
        Profile profile = new Profile();
        profile.setName(name);
        profile.setUser(user);
        return profile;
    }

    public static ProfileDTO createProfileDTO(String name, UserDTO user) {

        ProfileDTO profile = new ProfileDTO();
        profile.setLogin(user.getLogin());
        profile.setPassword(user.getPassword());
        profile.setRoles(user.getRoles());
        profile.setName(name);
        return profile;
    }
}
