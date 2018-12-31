package com.mbicycle.imarket.daos;

import com.mbicycle.imarket.beans.entities.Group;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Integer> {
    Group findByName(String name);

    List<Group> findByOrderByNameAsc();


    List<Group> findByNameOrderByCategoryName(String name);
}
