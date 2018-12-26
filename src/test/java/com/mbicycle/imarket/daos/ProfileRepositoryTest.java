package com.mbicycle.imarket.daos;

import com.mbicycle.imarket.Main;
import com.mbicycle.imarket.beans.entities.Profile;
import com.mbicycle.imarket.beans.entities.Role;
import com.mbicycle.imarket.beans.entities.User;
import com.mbicycle.imarket.utils.enums.RoleType;
import com.mbicycle.imarket.utils.generators.tests.TestObjectsBuilder;
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
public class ProfileRepositoryTest {

    private static final String TEST_PARAM = "test";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Before
    public void setUp() {
        User user = createUser(TEST_PARAM, TEST_PARAM);

        if (userRepository.findByLoginAndPassword(TEST_PARAM, TEST_PARAM) == null) {
            userRepository.save(user);
        }

        user = userRepository.findByLoginAndPassword(TEST_PARAM, TEST_PARAM);
        if (profileRepository.findByUser(user) == null) {
            profileRepository.save(createProfile(TEST_PARAM, user));
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
        roleRepository.findByOrderByRoleAsc().forEach(roleRepository::delete);
    }

    @Test
    public void check_of_profile() {
        User user = userRepository.findByLoginAndPassword(TEST_PARAM, TEST_PARAM);
        Profile profile = profileRepository.findByUser(user);
        assertNotNull(profile);
    }
}