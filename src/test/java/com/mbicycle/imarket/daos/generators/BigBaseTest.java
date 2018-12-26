package com.mbicycle.imarket.daos.generators;

import com.mbicycle.imarket.Main;
import com.mbicycle.imarket.beans.entities.Category;
import com.mbicycle.imarket.beans.entities.Group;
import com.mbicycle.imarket.beans.entities.Product;
import com.mbicycle.imarket.daos.CategoryRepository;
import com.mbicycle.imarket.daos.GroupRepository;
import com.mbicycle.imarket.daos.ProductRepository;
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
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
public class BigBaseTest {

    private static int countProductForOneGroup = 1_000;
    private List<Product> products;
    private List<Category> categories;
    private List<Group> groups;


    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private ProductRepository productRepository;

    @Before
    public void setUp() {
        System.out.println("*** Creating base data ***");
        BaseGenerator generator = new BaseGenerator();

        categories = generator.getCategories(); // get these 2 categories, it have to past generateProduсts()

        for (int i = 0; i < categories.size(); i++) {
            Category presentCategory = categoryRepository.findByName(categories.get(i).getName());
            if (presentCategory != null) {
                System.out.println("\n\nold category\n\n");
                categories.set(i, presentCategory);
            } else {
                System.out.println("\n\nnew category\n\n");
                categoryRepository.save(categories.get(i));
            }
        }

        generator.generateGroupsAndProducts(countProductForOneGroup);
        groups = generator.getGroups();
        groupRepository.saveAll(groups);

        products = generator.getProduсts();
        productRepository.saveAll(products);

    }

    @After
    public void tearDown() {
        System.out.println("*** Deleting BigBaseTest data ***");
        productRepository.deleteAll(products);
        groupRepository.deleteAll(groups);
        // categoryRepository.deleteAll(categories); // Alexey don't allow to delete any category?
    }

    public void clearBase() {
        System.out.println("*** Deleting all base data ***");
        productRepository.deleteAllInBatch();
        groupRepository.deleteAllInBatch();
        categoryRepository.deleteAllInBatch();
    }

    @Test
    public void check_of_adding_product() {
        long count = productRepository.count();
        System.out.println();
        System.out.println("count = " + count);
        System.out.println();
        assertTrue(count >= countProductForOneGroup * 4);
    }
}