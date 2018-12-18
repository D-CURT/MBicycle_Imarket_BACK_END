package com.mbicycle.imarket.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import com.mbicycle.imarket.beans.entities.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {
    Users findByName(String name);
}