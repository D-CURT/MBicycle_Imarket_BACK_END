package com.mbicycle.imarket.daos;

import com.mbicycle.imarket.beans.entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface GroupRepository extends JpaRepository<Group, Integer> {
    Group findByName(String name);
/*
    @Query("")
    List<Group> getAllSortedByName();*/
}
