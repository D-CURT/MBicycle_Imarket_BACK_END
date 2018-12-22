package com.mbicycle.imarket.utils;

import com.mbicycle.imarket.beans.entities.Role;

public enum RoleType {
    CUSTOMER {
        @Override
        public Role getRole() {
            return RoleType.create(this);
        }
    },
    MANAGER {
        @Override
        public Role getRole() {
            return RoleType.create(this);
        }
    },
    ADMIN {
        @Override
        public Role getRole() {
            return RoleType.create(this);
        }
    };

    public abstract Role getRole();

    private static Role create(RoleType type) {
        return new Role(type);
    }
}
