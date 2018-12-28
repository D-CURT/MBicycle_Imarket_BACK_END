package com.mbicycle.imarket.daos;

import com.mbicycle.imarket.beans.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByLoginAndPassword(String login, String password);

    List<User> findByOrderByLoginAsc();

    User findOneByLogin(String login);

}
