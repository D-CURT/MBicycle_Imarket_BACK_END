package com.mbicycle.imarket.daos;

import com.mbicycle.imarket.beans.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByLoginAndPassword(String name, String login);

    @Query("")
    List<User> getAllSortedByLogin();

    void deleteByLoginAndPassword(String name, String login);
}
