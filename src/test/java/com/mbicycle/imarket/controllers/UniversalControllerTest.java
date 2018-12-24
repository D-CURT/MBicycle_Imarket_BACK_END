package com.mbicycle.imarket.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mbicycle.imarket.Main;
import com.mbicycle.imarket.beans.entities.Category;
import com.mbicycle.imarket.beans.entities.Profile;
import com.mbicycle.imarket.beans.entities.Role;
import com.mbicycle.imarket.beans.entities.User;
import com.mbicycle.imarket.daos.CategoryRepository;
import com.mbicycle.imarket.daos.ProfileRepository;
import com.mbicycle.imarket.daos.RoleRepository;
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
import static com.mbicycle.imarket.utils.generators.tests.TestObjectsBuilder.createProfile;
import static com.mbicycle.imarket.utils.generators.tests.TestObjectsBuilder.createUser;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class UniversalControllerTest {
    private static final int ZERO = 0;
    private static final String FIRST_VALUE = "BBB";
    private static final String FIRST_USER_PASSWORD = "123";
    private static final String SECOND_VALUE = "CCC";
    private static final String SECOND_USER_PASSWORD = "321";
    private static final String THIRD_VALUE = "AAA";
    private static final String THIRD_USER_PASSWORD = "213";
    private List<Profile> profiles;
    private Category[] categories;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Before
    public void setUp() {
        User[] users = {createUser(FIRST_VALUE, FIRST_USER_PASSWORD)
                      , createUser(SECOND_VALUE, SECOND_USER_PASSWORD)
                      , createUser(THIRD_VALUE, THIRD_USER_PASSWORD)};

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

        Category[] categories = {new Category(FIRST_VALUE)
                             , new Category(SECOND_VALUE)
                             , new Category(THIRD_VALUE)};
        this.categories = categories;

        for (Category category: categories) {
            if (categoryRepository.findByName(category.getName()) == null) {
                System.out.println("*** Saving Caterogy. ***");
                categoryRepository.save(category);
            }
        }
    }

    @After
    public void tearDown() {
        profiles.forEach(profile -> profileRepository.delete(profile));
        roleRepository.findByOrderByRoleAsc().forEach(roleRepository::delete);

        for (Category category: categories) {
            if ((category = categoryRepository.findByName(category.getName())) != null) {
                System.out.println("*** Deleting Caterogy. ***");
                categoryRepository.delete(category);
            }
        }
    }

    @Test
    public void check_of_getting_sorted_by_login_users_list() throws Exception {
        String mapping = "/users/allUsersSortedByLogin";

        final List<User> EXPECTED_USER_LIST = userRepository.findByOrderByLoginAsc();
        List<User> actualUserList = actualList(mapping, User.class);

        assertThat(actualUserList.size(), is(greaterThan(ZERO)));
        assertThat(actualUserList, is(equalTo(EXPECTED_USER_LIST)));
    }

    @Test
    public void check_of_getting_sorted_by_name_profile_list() throws Exception {
        String mapping = "/profiles/allProfilesSortedByName";

        final List<Profile> EXPECTED_PROFILE_LIST = profileRepository.findByOrderByNameAsc();
        List<Profile> actualProfileList = actualList(mapping, Profile.class);

        assertThat(actualProfileList.size(), is(greaterThan(ZERO)));
        assertThat(actualProfileList, is(equalTo(EXPECTED_PROFILE_LIST)));
    }

    @Test
    public void check_of_getting_roles_sorted_by_role() throws Exception {
        String mapping = "/roles/allRolesSortedByRole";

        final List<Role> EXPECTED_ROLE_LIST = roleRepository.findByOrderByRoleAsc();
        List<Role> actualRoleList = actualList(mapping, Role.class);

        assertThat(actualRoleList.size(), is(greaterThan(ZERO)));
        assertThat(actualRoleList, is(equalTo(EXPECTED_ROLE_LIST)));
    }

    @Test
    public void check_of_getting_categories_sorted_by_name() throws Exception {
        String mapping = "/categories/allCategoriesSortedByName";

        final List<Category> EXPECTED_CATEGORY_LIST = categoryRepository.findByOrderByNameAsc();
        List<Category> actualCategoryList = actualList(mapping, Category.class);

        assertThat(actualCategoryList.size(), is(greaterThan(ZERO)));
        assertThat(actualCategoryList, is(equalTo(EXPECTED_CATEGORY_LIST)));
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

    private <T> List<T> actualList(String mapping, Class<T> type) throws Exception {
        List<T> actualRoleList = new ArrayList<>();
        ObjectMapper mapper = createMapper();

        for (JsonNode node: fillResultList(mvc, mapping, mapper)) {
            actualRoleList.add(mapper.treeToValue(node, type));
        }
        return actualRoleList;
    }
}