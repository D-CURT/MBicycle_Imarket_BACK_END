package com.mbicycle.imarket.utils.generators.tests;

import com.mbicycle.imarket.beans.entities.Role;
import com.mbicycle.imarket.beans.entities.User;
import com.mbicycle.imarket.utils.RoleType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestObjectsCreator {
//    private static final List<Role> ROLES = Arrays.asList(new Role(RoleType.CUSTOMER), new Role(RoleType.ADMIN));

    public static User createUser(String login, String password) {
        User user = new User(login, password);
        user.setRoles(Arrays.asList(RoleType.CUSTOMER.getRole(),RoleType.ADMIN.getRole()));
        return user;
    }

}
