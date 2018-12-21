package com.mbicycle.imarket.daos;

import com.mbicycle.imarket.Main;
import com.mbicycle.imarket.beans.entities.*;
import com.mbicycle.imarket.utils.RoleType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
public class OrderManagementTest {
    private static final String TEST_PARAM = "test";
    private static final List<Role> ROLES = Arrays.asList(new Role(RoleType.CUSTOMER), new Role(RoleType.ADMIN));

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Before
    public void setUp() {
        User user = new User(TEST_PARAM, TEST_PARAM);
        user.setRoles(ROLES);
        Profile profile = new Profile(TEST_PARAM, TEST_PARAM, TEST_PARAM, TEST_PARAM, user, "GG");
        if (userRepository.findByLoginAndPassword(TEST_PARAM, TEST_PARAM) == null) {
            System.out.println("*** Save User. ***");
            userRepository.save(user);
        }

        user = userRepository.findByLoginAndPassword(TEST_PARAM, TEST_PARAM);
        if (profileRepository.findByUser(user) == null) {
            System.out.println("*** Save Profile. ***");
            profileRepository.save(profile);
        }

        Order order = new Order(profile);
        OrderProduct op = new OrderProduct();
        order.setOrderProducts(Collections.singletonList(op));

        if ((order = orderRepository.findByProfile(profile)) == null) {
            System.out.println("*** Save order. ***");
            orderRepository.save(order);
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
            System.out.println("*** DELETING ***");
            profileRepository.delete(profile);
        }
    }

    @Test
    public void check_of_profile() {
        User user = userRepository.findByLoginAndPassword(TEST_PARAM, TEST_PARAM);
        Profile profile = profileRepository.findByUser(user);
        Order order = orderRepository.findByProfile(profile);
        assertNotNull(order);
    }
}