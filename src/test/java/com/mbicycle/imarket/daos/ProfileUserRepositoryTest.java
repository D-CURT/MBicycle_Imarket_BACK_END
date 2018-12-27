package com.mbicycle.imarket.daos;

import com.mbicycle.imarket.Main;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static com.mbicycle.imarket.utils.generators.tests.TestObjectsBuilder.createProfile;
import static com.mbicycle.imarket.utils.generators.tests.TestObjectsBuilder.createUser;
import static com.mbicycle.imarket.utils.generators.tests.TestObjectsBuilder.createOnlyUser;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
public class ProfileUserRepositoryTest {

    private static final String TEST_PARAM = "admin";

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
//        User user = userRepository.findByLoginAndPassword(TEST_PARAM, TEST_PARAM);
//        Profile profile = profileRepository.findByUser(user);
//        if (profile==null) {
//            System.out.println("*** Profile is null ***");
//        }
//        else {
//            System.out.println("*** DELETING ***");
//            profileRepository.delete(profile);
//        }
//        roleRepository.findByOrderByRoleAsc().forEach(roleRepository::delete);
    }

    @Test
    public void check_of_profile() {
        String str = new BCryptPasswordEncoder().encode("admin");

        System.out.println(str + "\n");

        System.out.println(str + "\n");
        User user = userRepository.findByLoginAndPassword(TEST_PARAM, TEST_PARAM);
        Profile profile = profileRepository.findByUser(user);
        assertNotNull(profile);
    }
}