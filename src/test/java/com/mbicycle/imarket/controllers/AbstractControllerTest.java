package com.mbicycle.imarket.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mbicycle.imarket.beans.dto.CategoryDTO;
import com.mbicycle.imarket.beans.dto.ProductDTO;
import com.mbicycle.imarket.beans.dto.ProfileDTO;
import com.mbicycle.imarket.beans.dto.UserDTO;
import com.mbicycle.imarket.beans.entities.*;
import com.mbicycle.imarket.daos.*;
import com.mbicycle.imarket.facades.interfaces.ProfileFacade;
import com.mbicycle.imarket.facades.interfaces.UserFacade;
import com.mbicycle.imarket.services.interfaces.*;
import com.mbicycle.imarket.utils.converters.Converter;
import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.databind.DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.mbicycle.imarket.utils.generators.tests.TestObjectsBuilder.createProfileDTO;
import static com.mbicycle.imarket.utils.generators.tests.TestObjectsBuilder.createUserDTO;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public abstract class AbstractControllerTest {
    static final int ZERO = 0;
    static final String FIRST_VALUE = "BBB";
    static final String FIRST_USER_PASSWORD = "123";
    static final String SECOND_VALUE = "CCC";
    static final String SECOND_USER_PASSWORD = "321";
    static final String THIRD_VALUE = "AAA";
    static final String THIRD_USER_PASSWORD = "213";
    List<Profile> profiles;
    List<Product> products;
    List<Category> categories;

    @Autowired
    MockMvc mvc;

    @Autowired
    UserService userService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryService categoryService;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    GroupService groupService;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @Autowired
    ProfileService profileService;

    @Autowired
    UserFacade userFacade;

    @Autowired
    ProfileFacade profileFacade;

    @Autowired
    Converter<UserDTO, User> userConverter;

    @Autowired
    Converter<Profile, ProfileDTO> profileConverter;

    @Autowired
    Converter<Product, ProductDTO> productConverter;

    @Autowired
    Converter<Category, CategoryDTO> categoryConverter;

    @Before
    public void setUp() {
        UserDTO[] users = {createUserDTO(FIRST_VALUE, FIRST_USER_PASSWORD)
                ,    createUserDTO(SECOND_VALUE, SECOND_USER_PASSWORD)
                ,    createUserDTO(THIRD_VALUE, THIRD_USER_PASSWORD)};

        profiles = new ArrayList<>();
        for (UserDTO dto: users) {
            userFacade.add(dto);
            profileFacade.add(createProfileDTO(dto.getLogin(), dto));
            profiles.add(profileRepository.findByUser(userService.get(dto.getLogin(), dto.getPassword())));
        }

        String[] names = {FIRST_VALUE
                , SECOND_VALUE
                , THIRD_VALUE};

        products = new ArrayList<>();
        categories = new ArrayList<>();

        for (String name: names) {

            System.out.println("*** Saving Caterogy. ***");
            categoryService.add(new Category(name));

            Category category = categoryService.get(name);
            categories.add(category);

            System.out.println("*** Saving Group. ***");
            groupService.add(new Group(name, category));

            System.out.println("*** Saving Product. ***");
            Product product = new Product();
            product.setName(name);
            product.setPrice(1.1);
            product.setGroup(groupService.get(name));
            productService.add(product);

            products.add(productRepository.findByName(name));
        }
    }

    @After
    public void tearDown() {
        profiles.forEach(profile -> {
            if (profileService.get(profile.getUser()) != null) {
                profileService.delete(profile);
            }
        });

        for (Category category: categories) {
            if ((category = categoryRepository.findByName(category.getName())) != null) {
                System.out.println("*** Deleting Category. ***");
                categoryRepository.delete(category);
            }
        }
    }

    ObjectMapper createMapper() {
        return new ObjectMapper()
                .configure(ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
                .configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    <T> List<T> actualList(String mapping, Class<T> type) throws Exception {
        List<T> actualRoleList = new ArrayList<>();
        ObjectMapper mapper = createMapper();

        for (JsonNode node: fillResultList(mvc, mapping, mapper)) {
            actualRoleList.add(mapper.treeToValue(node, type));
        }
        return actualRoleList;
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
