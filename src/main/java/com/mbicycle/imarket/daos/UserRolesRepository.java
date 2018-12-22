package com.mbicycle.imarket.daos;

import com.mbicycle.imarket.beans.entities.Role;
import com.mbicycle.imarket.beans.entities.User;
import com.mbicycle.imarket.beans.entities.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRolesRepository extends JpaRepository<UserRoles, Integer> {
    UserRoles findByUser(User user);

    UserRoles findByRole(Role role);
}
