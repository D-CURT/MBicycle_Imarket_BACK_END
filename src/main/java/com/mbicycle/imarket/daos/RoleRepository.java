package com.mbicycle.imarket.daos;

import com.mbicycle.imarket.beans.entities.Role;
import com.mbicycle.imarket.utils.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByRole(RoleType roleType);

    List<Role> findByOrderByRoleAsc();
}
