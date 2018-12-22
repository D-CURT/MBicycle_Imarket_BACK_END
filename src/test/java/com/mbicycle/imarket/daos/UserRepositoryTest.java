package com.mbicycle.imarket.daos;

import com.mbicycle.imarket.Main;
import com.mbicycle.imarket.beans.entities.Role;
import com.mbicycle.imarket.beans.entities.User;
import com.mbicycle.imarket.daos.UserRepository;
import com.mbicycle.imarket.utils.RoleType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
public class UserRepositoryTest {
    private static final String USER_LOGIN = "test";
    private static final String USER_PASSWORD = "test";
    private static final List<Role> ROLES = Arrays.asList(new Role(RoleType.CUSTOMER), new Role(RoleType.ADMIN));

    @Autowired
    private UserRepository repository;

    @Before
    public void setUp() {
        User user = new User(USER_LOGIN, USER_PASSWORD);
        user.setRoles(ROLES);
        if (repository.findByLoginAndPassword(USER_LOGIN, USER_PASSWORD) == null) {
            repository.save(user);
        }
    }

    @After
    public void tearDown() {
        User user;
        if ((user = repository.findByLoginAndPassword(USER_LOGIN, USER_PASSWORD)) != null) {
            repository.delete(user);
        }

    }

    @Test
    public void check_of_adding_user_with_role_list() {
        User user = repository.findByLoginAndPassword(USER_LOGIN, USER_PASSWORD);
        assertNotNull(user);
    }
}