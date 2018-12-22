package com.mbicycle.imarket.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mbicycle.imarket.Main;
import com.mbicycle.imarket.beans.entities.User;
import com.mbicycle.imarket.daos.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.databind.DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class UniversalControllerTest {
    private static final String FIRST_USER_LOGIN = "AAA";
    private static final String FIRST_USER_PASSWORD = "123";
    private static final String SECOND_USER_LOGIN = "CCC";
    private static final String SECOND_USER_PASSWORD = "321";
    private static final String THIRD_USER_LOGIN = "BBB";
    private static final String THIRD_USER_PASSWORD = "213";
    private User[] users;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() {
        User[] users = {new User(FIRST_USER_LOGIN, FIRST_USER_PASSWORD)
                      , new User(SECOND_USER_LOGIN, SECOND_USER_PASSWORD)
                      , new User(THIRD_USER_LOGIN, THIRD_USER_PASSWORD)};
        this.users = users;
        for (int i = users.length - 1; i >= 0; i--) {
            User currentUser = users[i];
            String login = currentUser.getLogin();
            String password = currentUser.getPassword();
            if (userRepository.findByLoginAndPassword(login, password) == null) {
                userRepository.save(currentUser);
            }
        }
    }

    @After
    public void tearDown() {
        for (User user: users) {
            String login = user.getLogin();
            String password = user.getPassword();
            if ((user = userRepository.findByLoginAndPassword(login, password)) != null) {
                userRepository.delete(user);
            }
        }
    }

    @Test
    public void check_of_getting_sorted_users_list() throws Exception {
        final List<User> EXPECTED_USER_LIST = userRepository.findByOrderByLoginAsc();

        byte[] responseBytes = mvc.perform(MockMvcRequestBuilders.get("/users/allUsersSortedByLogin")
                .param("offset", "0")
                .param("count", "1024"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsByteArray();

        ObjectMapper mapper = createMapper();
        ObjectNode[] nodes = mapper.readValue(responseBytes, ObjectNode[].class);

        List<User> actualUserList = new ArrayList<>();
        for (JsonNode node: nodes) {
            actualUserList.add(mapper.treeToValue(node, User.class));
        }

        assertThat(actualUserList, is(equalTo(EXPECTED_USER_LIST)));
        System.out.println(EXPECTED_USER_LIST);
    }

    private ObjectMapper createMapper() {
        return new ObjectMapper()
                .configure(ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
                .configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
}