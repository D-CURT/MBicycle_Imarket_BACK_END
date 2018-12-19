package com.mbicycle.imarket.daos;

import com.mbicycle.imarket.beans.entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Integer> {
}
