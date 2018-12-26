package com.mbicycle.imarket.utils.enums;

import com.mbicycle.imarket.beans.entities.Role;

public enum RoleType {
    CUSTOMER,
    MANAGER,
    ADMIN;

    public Role getRole() {
        return new Role(this);
    }
}
