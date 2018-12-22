package com.mbicycle.imarket.daos;

import com.mbicycle.imarket.Main;
import com.mbicycle.imarket.beans.entities.Category;
import com.mbicycle.imarket.beans.entities.Group;
import com.mbicycle.imarket.beans.entities.Product;
import com.mbicycle.imarket.utils.generators.BaseGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
public class ProductBigTest {
    private static final String TEST_TEXT_PARAM = "test";

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
            System.out.println("*** Saving Caterogy. ***");
            categoryRepository.save(category);

        }

        category = categoryRepository.findByName(TEST_TEXT_PARAM);
        Group group = new Group(TEST_TEXT_PARAM, category);

        if (groupRepository.findByName(TEST_TEXT_PARAM) == null) {

            System.out.println("*** Saving Group. ***");

            groupRepository.save(group);

        }

        group = groupRepository.findByName(TEST_TEXT_PARAM);
        Product product = new Product();
        product.setName(TEST_TEXT_PARAM);
        product.setGroup(group);
        if ((productRepository.findByName(TEST_TEXT_PARAM)) == null) {
            System.out.println("*** Saving Product. ***");
            productRepository.save(product);

            BaseGenerator generator = new BaseGenerator();
            List<Product> allProduct = generator.generateProduсts(1000);
            productRepository.saveAll(allProduct);
        }
    }

    @After
    public void tearDown() throws Exception {

                    Category category;
                    if((category = categoryRepository.findByName(TEST_TEXT_PARAM)) != null ) {
                        System.out.println("*** Deleting Category (Also groups and products). ***");
                        categoryRepository.delete(category);
                    }

    }

    @Test
    public void check_of_adding_product() {
        Product product = productRepository.findByName(TEST_TEXT_PARAM);
        assertNotNull(product);
    }
}