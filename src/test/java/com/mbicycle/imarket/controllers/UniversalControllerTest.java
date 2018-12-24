package com.mbicycle.imarket.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mbicycle.imarket.Main;
import com.mbicycle.imarket.beans.entities.Profile;
import com.mbicycle.imarket.beans.entities.Role;
import com.mbicycle.imarket.beans.entities.User;
import com.mbicycle.imarket.daos.ProfileRepository;
import com.mbicycle.imarket.daos.RoleRepository;
import com.mbicycle.imarket.daos.UserRepository;
import com.mbicycle.imarket.utils.enums.RoleType;
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
import static com.mbicycle.imarket.utils.generators.tests.TestObjectsBuilder.createProfile;
import static com.mbicycle.imarket.utils.generators.tests.TestObjectsBuilder.createUser;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class UniversalControllerTest {
    private static final String FIRST_USER_LOGIN = "BBB";
    private static final String FIRST_USER_PASSWORD = "123";
    private static final String SECOND_USER_LOGIN = "CCC";
    private static final String SECOND_USER_PASSWORD = "321";
    private static final String THIRD_USER_LOGIN = "AAA";
    private static final String THIRD_USER_PASSWORD = "213";
    private List<Profile> profiles;
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
        profiles = new ArrayList<>();
        for (User user: users) {
            String login = user.getLogin();
            String password = user.getPassword();
            if (userRepository.findByLoginAndPassword(login, password) == null) {
                userRepository.save(user);
            }

            user = userRepository.findByLoginAndPassword(login, password);

            if (profileRepository.findByUser(user) == null) {
                Profile profile = createProfile(user.getLogin(), user);
                profileRepository.save(profile);
                profiles.add(profile);
            }
        }
    }

    @After
    public void tearDown() {
        profiles.forEach(profile -> profileRepository.delete(profile));
        roleRepository.findByOrderByRoleAsc().forEach(roleRepository::delete);
    }

    @Test
    public void check_of_getting_sorted_by_login_users_list() throws Exception {
        final List<User> EXPECTED_USER_LIST = userRepository.findByOrderByLoginAsc();
        List<User> actualUserList = new ArrayList<>();
        ObjectMapper mapper = createMapper();

        for (JsonNode node: fillResultList(mvc, "/users/allUsersSortedByLogin", mapper)) {
            actualUserList.add(mapper.treeToValue(node, User.class));
        }
        assertThat(actualUserList, is(equalTo(EXPECTED_USER_LIST)));
    }

    @Test
    public void check_of_getting_sorted_by_name_profile_list() throws Exception {
        final List<Profile> EXPECTED_PROFILE_LIST = profileRepository.findByOrderByNameAsc();
        List<Profile> actualProfileList = new ArrayList<>();
        ObjectMapper mapper = createMapper();

        for (JsonNode node: fillResultList(mvc, "/profiles/allProfilesSortedByName", mapper)) {
            actualProfileList.add(mapper.treeToValue(node, Profile.class));
        }
        assertThat(actualProfileList, is(equalTo(EXPECTED_PROFILE_LIST)));
    }

    @Test
    public void check_of_getting_roles_sorted_by_role() throws Exception {
        final List<Role> EXPECTED_ROLE_LIST = roleRepository.findByOrderByRoleAsc();
        List<Role> actualRoleList = new ArrayList<>();
        ObjectMapper mapper = createMapper();

        for (JsonNode node: fillResultList(mvc, "/roles/allRolesSortedByRole", mapper)) {
            actualRoleList.add(mapper.treeToValue(node, Role.class));
        }
        assertThat(actualRoleList, is(equalTo(EXPECTED_ROLE_LIST)));
    }

    private ObjectMapper createMapper() {
        return new ObjectMapper()
                .configure(ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
                .configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private ObjectNode[] fillResultList(MockMvc mvc, String mapping, ObjectMapper mapper) throws Exception {
        byte[] responseBytes = mvc.perform(MockMvcRequestBuilders.get(mapping)
                .param("offset", "0")
                .param("count", "2048"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsByteArray();

        return mapper.readValue(responseBytes, ObjectNode[].class);
    }
}