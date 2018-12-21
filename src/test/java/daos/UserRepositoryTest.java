package daos;

import com.mbicycle.imarket.Main;
import com.mbicycle.imarket.beans.entities.Role;
import com.mbicycle.imarket.beans.entities.User;
import com.mbicycle.imarket.controllers.UniversalController;
import com.mbicycle.imarket.daos.UserRepository;
import com.mbicycle.imarket.services.UserService;
import com.mbicycle.imarket.utils.RoleType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
//@WebMvcTest(UniversalController.class)
@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
public class UserRepositoryTest {
    private static final String USER_LOGIN = "test";
    private static final String USER_PASSWORD = "test";
    private static final List<Role> ROLES = Arrays.asList(new Role(RoleType.CUSTOMER), new Role(RoleType.ADMIN));
    @Autowired
    private MockMvc mock;

/*    @MockBean
    private UserService service;*/
@MockBean
private UserRepository repository;

    @Before
    public void setUp() throws Exception {
        User user = new User(USER_LOGIN, USER_PASSWORD);
        user.setRoles(ROLES);
//        service.addUser(user);
        given(repository.findByLoginAndPassword(USER_LOGIN, USER_PASSWORD)).willReturn(user);
    }

    /*@After
    public void tearDown() throws Exception {
        service.deleteUser(USER_LOGIN, USER_PASSWORD);
    }*/

    @Test
    public void getAllSortedByLogin() {
    }

    @Test
    public void addUser() {
        User user = repository.findByLoginAndPassword(USER_LOGIN, USER_PASSWORD);
        assertNotNull(user);
    }


}