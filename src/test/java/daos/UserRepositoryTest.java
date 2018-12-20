package daos;

import com.mbicycle.imarket.beans.entities.Role;
import com.mbicycle.imarket.beans.entities.User;
import com.mbicycle.imarket.daos.UserRepository;
import com.mbicycle.imarket.services.UserService;
import com.mbicycle.imarket.utils.RoleType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    private static final String USER_LOGIN = "test";
    private static final String USER_PASSWORD = "test";
    private static final List<Role> ROLES = Arrays.asList(new Role(RoleType.CUSTOMER), new Role(RoleType.ADMIN));

    @TestConfiguration
    static class UserTestConfig {
        @Bean
        public UserService getUserService() {
            return new UserService();
        }
    }

    @Autowired
    private UserService service;

    @Before
    public void setUp() throws Exception {
        User user = new User(USER_LOGIN, USER_PASSWORD);
        user.setRoles(ROLES);
        service.addUser(user);
    }

    @After
    public void tearDown() throws Exception {
        service.deleteUser(USER_LOGIN, USER_PASSWORD);
    }

    @Test
    public void getAllSortedByLogin() {
    }

    @Test
    public void addUser() {
        User user = service.getUser(USER_LOGIN, USER_PASSWORD);
        assertNotNull(user);
    }
}