package com.mbicycle.imarket.daos;

import com.mbicycle.imarket.Main;
import com.mbicycle.imarket.beans.entities.Category;
import com.mbicycle.imarket.beans.entities.Group;
import com.mbicycle.imarket.beans.entities.Product;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
public class ProductManagementTest {
    private static final String TEST_TEXT_PARAM = "test";
    private static final double TEST_DOUBLE_PARAM = 2.2;
    private static final int TEST_INT_PARAM = 1;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private ProductRepository productRepository;

    @Before
    public void setUp() {
        Category category = new Category(TEST_TEXT_PARAM);

        if (categoryRepository.findByName(TEST_TEXT_PARAM) == null) {
            categoryRepository.save( category);
        }

        category = categoryRepository.findByName(TEST_TEXT_PARAM);
        Group group = new Group(TEST_TEXT_PARAM, category);

        if (groupRepository.findByName(TEST_TEXT_PARAM) == null) {
            groupRepository.save(group);
        }

        group = groupRepository.findByName(TEST_TEXT_PARAM);
        Product product = new Product(TEST_TEXT_PARAM, TEST_TEXT_PARAM, TEST_TEXT_PARAM
                , TEST_DOUBLE_PARAM, TEST_TEXT_PARAM, false, TEST_INT_PARAM, group);
        if ((productRepository.findByName(TEST_TEXT_PARAM)) == null) {
            productRepository.save(product);
        }
    }

    @After
    public void tearDown() {
        Product product;
        if ((product = productRepository.findByName(TEST_TEXT_PARAM)) != null) {
            productRepository.delete(product);
            Category category = categoryRepository.findByName(TEST_TEXT_PARAM);
            categoryRepository.delete(category);
        }
    }

    @Test
    public void check_of_adding_product() {
        Product product = productRepository.findByName(TEST_TEXT_PARAM);
        assertNotNull(product);
    }
}