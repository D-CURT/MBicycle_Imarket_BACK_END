package com.mbicycle.imarket.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mbicycle.imarket.Main;
import com.mbicycle.imarket.beans.entities.Role;
import com.mbicycle.imarket.beans.entities.User;
import com.mbicycle.imarket.daos.ProfileRepository;
import com.mbicycle.imarket.daos.RoleRepository;
import com.mbicycle.imarket.daos.UserRepository;
import com.mbicycle.imarket.utils.RoleType;
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
import java.util.Arrays;
import java.util.List;

import static com.fasterxml.jackson.databind.DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.mbicycle.imarket.utils.generators.tests.TestObjectsCreator.createUser;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class UniversalControllerTest {
//    private static final List<Role> ROLES = Arrays.asList(new Role(RoleType.CUSTOMER), new Role(RoleType.ADMIN));
    private static final String FIRST_USER_LOGIN = "AAA";
    private static final String FIRST_USER_PASSWORD = "123";
    private static final String SECOND_USER_LOGIN = "CCC";
    private static final String SECOND_USER_PASSWORD = "321";
    private static final String THIRD_USER_LOGIN = "BBB";
    private static final String THIRD_USER_PASSWORD = "213";
    private static final List<Role> ROLES =
            Arrays.asList(new Role(RoleType.CUSTOMER)
                        , new Role(RoleType.ADMIN));
    private User[] users;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Before
    public void setUp() {
        User[] users = {createUser(FIRST_USER_LOGIN, FIRST_USER_PASSWORD)
                      , createUser(SECOND_USER_LOGIN, SECOND_USER_PASSWORD)
                      , createUser(THIRD_USER_LOGIN, THIRD_USER_PASSWORD)};
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
        Arrays.stream(users).forEach(user -> userRepository.delete(user));
    }

    @Test
    public void check_of_getting_sorted_by_login_users_list() throws Exception {
        final List<User> EXPECTED_USER_LIST_ASC = userRepository.findByOrderByLoginAsc();

        List<User> actualUserList = new ArrayList<>();
        ObjectMapper mapper = createMapper();

        for (JsonNode node: fillResultList(mvc, "/users/allUsersSortedByLogin", mapper)) {
            actualUserList.add(mapper.treeToValue(node, User.class));
        }
        assertThat(actualUserList, is(equalTo(EXPECTED_USER_LIST_ASC)));
        System.out.println(EXPECTED_USER_LIST_ASC);
    }

    public void check_of_getting_sorted_by_name_profile_list() {

    }

    private ObjectMapper createMapper() {
        return new ObjectMapper()
                .configure(ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
                .configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private ObjectNode[] fillResultList(MockMvc mvc, String mapping, ObjectMapper mapper) throws Exception {
        byte[] responseBytes = mvc.perform(MockMvcRequestBuilders.get(mapping)
                .param("offset", "0")
                .param("count", "1024"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsByteArray();

        return mapper.readValue(responseBytes, ObjectNode[].class);
    }
}