package com.mbicycle.imarket.daos;

import com.mbicycle.imarket.beans.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Query("")
    List<Role> getAllSortedByRole();
}
