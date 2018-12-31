package com.mbicycle.imarket.daos;

import com.mbicycle.imarket.Main;
import com.mbicycle.imarket.beans.entities.*;
import com.mbicycle.imarket.utils.enums.RoleType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static com.mbicycle.imarket.utils.generators.tests.TestObjectsBuilder.createProfile;
import static com.mbicycle.imarket.utils.generators.tests.TestObjectsBuilder.createUser;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
public class OrderManagementTest {
    private static final String TEST_PARAM = "test";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Before
    public void setUp() {
        User user = createUser(TEST_PARAM, TEST_PARAM);
        if (userRepository.findByLoginAndPassword(TEST_PARAM, TEST_PARAM) == null) {
            System.out.println("*** Save User. ***");
            userRepository.save(user);
        }

        user = userRepository.findByLoginAndPassword(TEST_PARAM, TEST_PARAM);
        Profile profile = createProfile(TEST_PARAM, user);
        if (profileRepository.findByUser(user) == null) {
            System.out.println("*** Save Profile. ***");
            profileRepository.save(profile);
        }
        Order order;

        if (orderRepository.findByProfile(profile) == null) {
            System.out.println("*** Save order. ***");
            order = new Order();
            order.setProfile(profile);

            orderRepository.save(order);
        }

        order = orderRepository.findByProfile(profile);

        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setOrder(order);
        if (orderProductRepository.findByOrder(order) == null) {
            System.out.println("*** Save OrderProduct ***");
            orderProductRepository.save(orderProduct);
        }
    }

    @After
    public void tearDown() {
        User user = userRepository.findByLoginAndPassword(TEST_PARAM, TEST_PARAM);
        Profile profile = profileRepository.findByUser(user);
        if (profile==null) {
            System.out.println("*** Profile is null ***");
        }
        else {
            if (orderRepository.findByProfile(profile) != null) {
                System.out.println("*** DELETING PROFILE***");
                profileRepository.delete(profile);
            }
        }
    }

    @Test
    public void check_of_order() {
        User user = userRepository.findByLoginAndPassword(TEST_PARAM, TEST_PARAM);
        Profile profile = profileRepository.findByUser(user);
        Order order = null;
        for (Order o: profile.getOrders()) {
            if (o.getDateOpened() == null) {
                order = o;
            }
        }
        assertNotNull(order);
    }
}