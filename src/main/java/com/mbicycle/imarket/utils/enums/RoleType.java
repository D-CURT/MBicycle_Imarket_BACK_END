package com.mbicycle.imarket.utils.enums;

import com.mbicycle.imarket.beans.entities.Role;

import java.util.Arrays;

public enum RoleType {
    CUSTOMER,
    MANAGER,
    ADMIN;

    public Role getRole() {
        return new Role(this);
    }

    public static String[] names() {
        return Arrays.stream(values()).map(Enum::name).toArray(String[]::new);
    }
}
