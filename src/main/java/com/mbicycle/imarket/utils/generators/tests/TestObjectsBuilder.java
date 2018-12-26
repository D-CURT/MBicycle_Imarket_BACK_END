package com.mbicycle.imarket.utils.generators.tests;

import com.mbicycle.imarket.beans.entities.Profile;
import com.mbicycle.imarket.beans.entities.User;

import java.util.Arrays;

import static com.mbicycle.imarket.utils.enums.RoleType.ADMIN;
import static com.mbicycle.imarket.utils.enums.RoleType.CUSTOMER;

public class TestObjectsBuilder {

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
}
