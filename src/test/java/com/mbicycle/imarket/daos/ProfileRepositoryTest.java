package com.mbicycle.imarket.daos;

import com.mbicycle.imarket.Main;
import com.mbicycle.imarket.beans.entities.Profile;
import com.mbicycle.imarket.beans.entities.Role;
import com.mbicycle.imarket.beans.entities.User;
import com.mbicycle.imarket.utils.RoleType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
public class ProfileRepositoryTest {

    private static final String TEST_PARAM = "test";
    private static final List<Role> ROLES = Arrays.asList(new Role(RoleType.CUSTOMER), new Role(RoleType.ADMIN));

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Before
    public void setUp() {
        User user = new User(TEST_PARAM, TEST_PARAM);
        user.setRoles(ROLES);
        Profile profile = new Profile(TEST_PARAM, TEST_PARAM, TEST_PARAM, TEST_PARAM, user, "GG");
        if (userRepository.findByLoginAndPassword(TEST_PARAM, TEST_PARAM) == null) {
            userRepository.save(user);
        }

        user = userRepository.findByLoginAndPassword(TEST_PARAM, TEST_PARAM);
        if (profileRepository.findByUser(user) == null) {
            System.out.println(2);
            profileRepository.save(profile);
        }
    }

    @After
    public void tearDown() {
        User user = userRepository.findByLoginAndPassword(TEST_PARAM, TEST_PARAM);
        Profile profile;
        if ((profile = profileRepository.findByUser(user)) != null) {
            profileRepository.delete(profile);
        }
    }

    @Test
    public void check_of_profile() {
        User user = userRepository.findByLoginAndPassword(TEST_PARAM, TEST_PARAM);
        Profile profile = profileRepository.findByUser(user);
        assertNotNull(profile);
    }
}