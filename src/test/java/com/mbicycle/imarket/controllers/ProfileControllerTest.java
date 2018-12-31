package com.mbicycle.imarket.controllers;

import com.mbicycle.imarket.Main;
import com.mbicycle.imarket.beans.dto.ProfileDTO;
import com.mbicycle.imarket.beans.dto.UserDTO;
import com.mbicycle.imarket.beans.entities.Profile;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

import static com.mbicycle.imarket.utils.generators.tests.TestObjectsBuilder.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
public class ProfileControllerTest extends AbstractControllerTest {

    @Test
    public void check_of_getting_sorted_by_name_profile_list() throws Exception {
        String mapping = "/profiles/allProfilesSortedByName";

        final List<ProfileDTO> EXPECTED_PROFILE_LIST = profileRepository.findByOrderByNameAsc()
                .stream()
                .map(profileConverter::convert)
                .collect(Collectors.toList());

        List<ProfileDTO> actualProfileList = actualList(mapping, ProfileDTO.class);

        assertThat(actualProfileList.size(), Is.is(greaterThan(ZERO)));
        assertThat(actualProfileList, Is.is(equalTo(EXPECTED_PROFILE_LIST)));
    }

    @Test
    public void check_of_getting_profile_by_user() throws Exception {

        UserDTO dto = createUserDTO(FIRST_VALUE, FIRST_USER_PASSWORD);
        String json = createMapper().writeValueAsString(createProfileDTO(FIRST_VALUE, dto));
        Profile profile = profileService.get(userConverter.convert(dto));
        String expected = createMapper().writeValueAsString(profileConverter.convert(profile));


        mvc.perform(get("/profiles/get").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                                                   .content(json))
           .andExpect(content().string(is(equalTo(expected))));
    }
}