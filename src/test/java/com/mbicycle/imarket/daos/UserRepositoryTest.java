package com.mbicycle.imarket.daos;

import com.mbicycle.imarket.Main;
import com.mbicycle.imarket.beans.entities.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.mbicycle.imarket.utils.generators.tests.TestObjectsCreator.createUser;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
public class UserRepositoryTest {
    private static final String USER_LOGIN = "test";
    private static final String USER_PASSWORD = "test";
    private User user;

    @Autowired
    private UserRepository repository;

    @Before
    public void setUp() {
        this.user = createUser(USER_LOGIN, USER_PASSWORD);
        if (repository.findByLoginAndPassword(USER_LOGIN, USER_PASSWORD) == null) {
            repository.save(user);
        }
    }

    @After
    public void tearDown() {
        repository.delete(user);
    }

    @Test
    public void check_of_adding_user_with_role_list() {
        assertNotNull(user);
    }
}