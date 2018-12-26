package com.mbicycle.imarket.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mbicycle.imarket.Main;
import com.mbicycle.imarket.beans.entities.*;
import com.mbicycle.imarket.daos.*;
import com.mbicycle.imarket.services.UserService;
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
    private List<Product> products;
    private Category[] categories;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private ProductRepository productRepository;

    @Before
    public void setUp() {
        User[] users = {createUser(FIRST_VALUE, FIRST_USER_PASSWORD)
                      , createUser(SECOND_VALUE, SECOND_USER_PASSWORD)
                      , createUser(THIRD_VALUE, THIRD_USER_PASSWORD)};

        profiles = new ArrayList<>();
        for (User user: users) {
            String login = user.getLogin();
            String password = user.getPassword();
            if (userService.getUser(login, password) == null) {
                userService.addUser(user);
            }

            user = userService.getUser(login, password);

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

        products = new ArrayList<>();
        for (Category category: categories) {
            String name = category.getName();
            if (categoryRepository.findByName(name) == null) {
                System.out.println("*** Saving Caterogy. ***");
                categoryRepository.save(category);
            }

            category = categoryRepository.findByName(name);
            Group group = new Group(name, category);

            if (groupRepository.findByName(name) == null) {
                System.out.println("*** Saving Group. ***");
                groupRepository.save(group);
            }

            group = groupRepository.findByName(name);
            Product product = new Product();
            product.setName(name);
            product.setGroup(group);
            if (productRepository.findByName(name) == null) {
                System.out.println("*** Saving Product. ***");
                productRepository.save(product);
            }
            products.add(productRepository.findByName(name));
        }
    }

    @After
    public void tearDown() {
        profiles.forEach(profile -> profileRepository.delete(profile));
        roleRepository.findByOrderByRoleAsc().forEach(roleRepository::delete);

        for (Category category: categories) {
            if ((category = categoryRepository.findByName(category.getName())) != null) {
                System.out.println("*** Deleting Category. ***");
                categoryRepository.delete(category);
            }
        }
    }

    @Test
    public void check_of_getting_sorted_by_login_users_list() throws Exception {
        String mapping = "/users/allUsersSortedByLogin";

        final List<User> EXPECTED_USER_LIST = userService.findByOrderByLogin();
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

    @Test
    public void check_of_getting_groups_sorted_by_name() throws Exception {
        String mapping = "/groups/allGroupsSortedByName";

        final List<Group> EXPECTED_GROUP_LIST = groupRepository.findByOrderByNameAsc();
        List<Group> actualGroupList = actualList(mapping, Group.class);

        assertThat(actualGroupList.size(), is(greaterThan(ZERO)));
        assertThat(actualGroupList, is(equalTo(EXPECTED_GROUP_LIST)));
    }

    @Test
    public void check_of_getting_products_by_group_sorted_by_name() throws Exception {
        String mapping = "/products/allProductsWithGroupSortedByName/";
        List<Product> expectedProductList = new ArrayList<>();
        List<Product> actualProductList = new ArrayList<>();
        for (Product product: products) {
            expectedProductList.add(
                    productRepository.findByGroupOrderByNameAsc(
                            product.getGroup()).get(ZERO));
            actualProductList.add(actualList(
                    mapping + product.getGroup()
                                              .getName()
                    , Product.class).get(ZERO));
        }

        assertThat(actualProductList.size(), is(greaterThan(ZERO)));
        assertThat(actualProductList, is(equalTo(expectedProductList)));
    }

    @Test
    public void check_of_getting_products_by_group_sorted_by_price() throws Exception {
        String mapping = "/products/allProductsWithGroupSortedByPrice/";
        List<Product> expectedProductList = new ArrayList<>();
        List<Product> actualProductList = new ArrayList<>();
        for (Product product: products) {
            expectedProductList.add(
                    productRepository.findByGroupOrderByPriceAsc(
                            product.getGroup()).get(ZERO));
            actualProductList.add(actualList(
                    mapping + product.getGroup()
                                              .getName()
                    , Product.class).get(ZERO));
        }

        assertThat(actualProductList.size(), is(greaterThan(ZERO)));
        assertThat(actualProductList, is(equalTo(expectedProductList)));
    }

    @Test
    public void check_of_getting_products_with_name_like_sorted_by_name() throws Exception {
        String mapping = "/products/allProductsSortedByNameWithNameLike/";
        List<Product> expectedProductList = new ArrayList<>();
        List<Product> actualProductList = new ArrayList<>();

        for (Product product: products) {
            String screen = "%";
            String name = screen + product.getName().charAt(ZERO) + screen;
            expectedProductList.add(
                    productRepository.findByNameLikeOrderByNameAsc(name).get(ZERO));
            actualProductList.add(actualList(
                    mapping + name
                    , Product.class).get(ZERO));
        }

        assertThat(actualProductList.size(), is(greaterThan(ZERO)));
        assertThat(actualProductList, is(equalTo(expectedProductList)));
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