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

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private ProductRepository productRepository;

    @Before
    public void setUp() {


            BaseGenerator generator = new BaseGenerator();
            List<Product> allProduct = generator.generateProduсts(25000);
            List<Category> categories = generator.getCategories(); // get these 2 categories, it have to past generateProduсts()
            List<Group> groups = generator.getGroups(); // get these 2 * 2 = 4 categories, it have to past generateProduсts()
            categoryRepository.saveAll(categories);
            groupRepository.saveAll(groups);
            productRepository.saveAll(allProduct);

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void check_of_adding_product() {
//        Product product = productRepository.findByName(TEST_TEXT_PARAM);
//        assertNotNull(product);
    }
}