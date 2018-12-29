package com.mbicycle.imarket.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mbicycle.imarket.Main;
import com.mbicycle.imarket.beans.dto.UserDTO;
import com.mbicycle.imarket.beans.entities.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.databind.DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.mbicycle.imarket.utils.generators.tests.TestObjectsBuilder.createUserDTO;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
public class UserControllerTest extends AbstractControllerTest {

    @Test
    public void check_of_getting_user_by_Login() {

    }

    @Test
    public void check_of_getting_sorted_by_login_users_list() throws Exception {
        String mapping = "/users/allUsersSortedByLogin";


        final List<User> EXPECTED_USER_LIST = userService.findByOrderByLogin();
        List<UserDTO> actualUserList = actualList(mapping, UserDTO.class);

        assertThat(actualUserList.size(), is(greaterThan(ZERO)));
        assertThat(actualUserList, is(equalTo(EXPECTED_USER_LIST)));
    }

    @Test
    public void check_of_user_adding() throws Exception {
        String json = createMapper().writeValueAsString(createUserDTO(FIRST_VALUE, FIRST_USER_PASSWORD));
        mvc.perform(MockMvcRequestBuilders.post("/users/add")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json))
                .andExpect(status().isOk());
    }

    @Test
    public void check_of_deleting_user() throws Exception {
        String json = createMapper().writeValueAsString(createUserDTO(SECOND_VALUE, SECOND_USER_PASSWORD));

        mvc.perform(MockMvcRequestBuilders.post("/users/delete")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json))
                .andExpect(status().isOk());
    }
}