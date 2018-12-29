package com.mbicycle.imarket.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mbicycle.imarket.Main;
import com.mbicycle.imarket.beans.dto.UserDTO;
import com.mbicycle.imarket.beans.entities.User;
import com.mbicycle.imarket.utils.converters.Converter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static com.mbicycle.imarket.utils.generators.tests.TestObjectsBuilder.createUserDTO;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
public class UserControllerTest extends AbstractControllerTest {

    @Autowired
    private Converter<User, UserDTO> converter;

    @Test
    public void check_of_getting_user_by_Login() throws Exception {
        String json = createMapper().writeValueAsString(converter.convert(userService.get(THIRD_VALUE, THIRD_USER_PASSWORD)));
        mvc.perform(get("/users/get").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                                                .content(json))
           .andExpect(content().string(json));
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
        mvc.perform(post("/users/add")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json))
                .andExpect(status().isOk());
    }

    @Test
    public void check_of_deleting_user() throws Exception {
        String json = createMapper().writeValueAsString(createUserDTO(SECOND_VALUE, SECOND_USER_PASSWORD));
        mvc.perform(post("/users/delete")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json))
                .andExpect(status().isOk());
    }
}