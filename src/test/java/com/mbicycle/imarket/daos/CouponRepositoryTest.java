package com.mbicycle.imarket.daos;

import com.mbicycle.imarket.Main;
import com.mbicycle.imarket.beans.entities.Coupon;
import com.mbicycle.imarket.beans.entities.Profile;
import com.mbicycle.imarket.beans.entities.Role;
import com.mbicycle.imarket.beans.entities.User;
import com.mbicycle.imarket.utils.enums.RoleType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mbicycle.imarket.utils.generators.tests.TestObjectsBuilder.createProfile;
import static com.mbicycle.imarket.utils.generators.tests.TestObjectsBuilder.createUser;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
public class CouponRepositoryTest {

    private static final String TEST_PARAM = "test";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    CouponRepository couponRepository;

    @Before
    public void setUp() {
        User user = createUser(TEST_PARAM, TEST_PARAM);
        if (userRepository.findByLoginAndPassword(TEST_PARAM, TEST_PARAM) == null) {
            System.out.println("***  Saving user. ***");
            userRepository.save(user);
        }

        user = userRepository.findByLoginAndPassword(TEST_PARAM, TEST_PARAM);
        Profile profile = createProfile(TEST_PARAM, user);
        if (profileRepository.findByUser(user) == null) {
            System.out.println("*** Saving profile. ***");
            profileRepository.save(profile);
        }


        List<Profile> profiles = new ArrayList<>();
        profiles.add(profile);

        Coupon coupon = new Coupon(TEST_PARAM, 1, profiles);
        if (couponRepository.findByDescription(TEST_PARAM) == null) {
            System.out.println("***  Saving coupon. ***");
            couponRepository.save(coupon);
        }

    }

    @After
    public void tearDown() {
        User user = userRepository.findByLoginAndPassword(TEST_PARAM, TEST_PARAM);
        Profile profile = profileRepository.findByUser(user);
        if (profile==null) {
            System.out.println("*** Profile is null ***");
        } else {
            System.out.println("*** Profile IS NOT NULL ***");
            Coupon coupon;
            if((coupon = couponRepository.findByDescription(TEST_PARAM)) !=null) {
                System.out.println("*** Deleting Coupon ***");
                couponRepository.delete(coupon);

                System.out.println("*** Deleting Profile ***");
                profileRepository.delete(profile);
            }
        }
    }

    @Test
    public void checkCoupons() {
        Coupon coupon = couponRepository.findByDescription(TEST_PARAM);
        assertNotNull(coupon);
    }
}