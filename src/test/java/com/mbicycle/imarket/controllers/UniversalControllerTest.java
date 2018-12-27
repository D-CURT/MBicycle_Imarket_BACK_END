package com.mbicycle.imarket.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mbicycle.imarket.Main;
import com.mbicycle.imarket.beans.entities.*;
import com.mbicycle.imarket.daos.*;
import com.mbicycle.imarket.services.CategoryService;
import com.mbicycle.imarket.services.GroupService;
import com.mbicycle.imarket.services.ProductService;
import com.mbicycle.imarket.services.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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
    private List<Category> categories;

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
    private CategoryService categoryService;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupService groupService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

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

        String[] names = {FIRST_VALUE
                             , SECOND_VALUE
                             , THIRD_VALUE};

        products = new ArrayList<>();
        categories = new ArrayList<>();

        for (String name: names) {

            /*System.out.println("*** Saving Caterogy. ***");
            categoryService.addCategory(name);

            Category category = categoryService.getCategory(name);
            categories.add(category);

            System.out.println("*** Saving Group. ***");
            groupService.addGroup(name, category.getName());*/

            System.out.println("*** Saving Product. ***");
//            productService.addProduct(name, 1.1, name, name);

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

    @Test
    public void check_of_adding_product() throws Exception {
        String mapping = "/products/addTest";

        String EXPECTED_PRODUCT_NAME = productService.getProduct(FIRST_VALUE).getName();
        mvc.perform(MockMvcRequestBuilders.post(
                mapping + "?name=" + FIRST_VALUE
                                   + "&price=" + 1.1
                                   + "&group=" + FIRST_VALUE
                                   + "&category=" + FIRST_VALUE).accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", is(equalTo(EXPECTED_PRODUCT_NAME))));
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