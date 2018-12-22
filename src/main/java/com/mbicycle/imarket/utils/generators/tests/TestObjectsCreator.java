package com.mbicycle.imarket.utils.generators.tests;

import com.mbicycle.imarket.beans.entities.User;
import java.util.Arrays;

import static com.mbicycle.imarket.utils.RoleType.ADMIN;
import static com.mbicycle.imarket.utils.RoleType.CUSTOMER;

public class TestObjectsCreator {

    public static User createUser(String login, String password) {
        return new User(login, password
                , Arrays.asList(CUSTOMER.getRole(),ADMIN.getRole()));
    }

}
