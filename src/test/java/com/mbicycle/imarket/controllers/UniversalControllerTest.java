package com.mbicycle.imarket.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mbicycle.imarket.Main;
import com.mbicycle.imarket.beans.entities.*;
import com.mbicycle.imarket.beans.dto.CategoryDTO;
import com.mbicycle.imarket.beans.dto.ProductDTO;
import com.mbicycle.imarket.beans.dto.ProfileDTO;
import com.mbicycle.imarket.beans.dto.UserDTO;
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
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class UniversalControllerTest extends AbstractControllerTest{

    // =========== PROFILE ===========

    @Test
    public void check_of_getting_sorted_by_name_profile_list() throws Exception {
        String mapping = "/profiles/allProfilesSortedByName";

        final List<ProfileDTO> EXPECTED_PROFILE_LIST = profileRepository.findByOrderByNameAsc()
                                                                        .stream()
                                                                        .map(profileConverter::convert)
                                                                        .collect(Collectors.toList());

        List<ProfileDTO> actualProfileList = actualList(mapping, ProfileDTO.class);

        assertThat(actualProfileList.size(), is(greaterThan(ZERO)));
        assertThat(actualProfileList, is(equalTo(EXPECTED_PROFILE_LIST)));
    }

    @Test
    public void check_of_getting_categories_sorted_by_name() throws Exception {
        String mapping = "/categories/allCategoriesSortedByName";

        final List<CategoryDTO> EXPECTED_CATEGORY_LIST = categoryRepository.findByOrderByNameAsc()
                          .stream()
                          .map(categoryConverter::convert)
                          .collect(Collectors.toList());
        List<CategoryDTO> actualCategoryList = actualList(mapping, CategoryDTO.class);

        assertThat(actualCategoryList.size(), is(greaterThan(ZERO)));
        assertThat(actualCategoryList, is(equalTo(EXPECTED_CATEGORY_LIST)));
    }

    @Test
    public void check_of_getting_groups_sorted_by_name() throws Exception {
        String mapping = "/groups/allGroupsSortedByName";

        final List<Group> EXPECTED_GROUP_LIST = groupRepository.findByOrderByNameAsc();
        List<Group> actualGroupList = actualList(mapping, Group.class);

        assertThat(actualGroupList.size(), is(greaterThan(ZERO)));
        assertThat(actualGroupList, is(equalTo(EXPECTED_GROUP_LIST)));
    }

    @Test
    public void check_of_getting_products_by_group_sorted_by_name() throws Exception {
        String mapping = "/products/allProductsWithGroupSortedByName/";
        List<ProductDTO> expectedProductList = new ArrayList<>();
        List<ProductDTO> actualProductList = new ArrayList<>();
        for (Product product: products) {
            expectedProductList.add(
                    productConverter.convert(productRepository.findByGroupOrderByNameAsc(
                            product.getGroup()).get(ZERO)));
            actualProductList.add(actualList(
                    mapping + product.getGroup()
                                              .getName()
                    , ProductDTO.class).get(ZERO));
        }

        assertThat(actualProductList.size(), is(greaterThan(ZERO)));
        assertThat(actualProductList, is(equalTo(expectedProductList)));
    }

    @Test
    public void check_of_getting_products_by_group_sorted_by_price() throws Exception {
        String mapping = "/products/allProductsWithGroupSortedByPrice/";
        List<ProductDTO> expectedProductList = new ArrayList<>();
        List<ProductDTO> actualProductList = new ArrayList<>();
        for (Product product: products) {
            expectedProductList.add(
                     productConverter.convert(productRepository.findByGroupOrderByPriceAsc(
                            product.getGroup()).get(ZERO)));
            actualProductList.add(actualList(
                    mapping + product.getGroup()
                                              .getName()
                    , ProductDTO.class).get(ZERO));
        }

        assertThat(actualProductList.size(), is(greaterThan(ZERO)));
        assertThat(actualProductList, is(equalTo(expectedProductList)));
    }

    @Test
    public void check_of_getting_products_with_name_like_sorted_by_name() throws Exception {
        String mapping = "/products/allProductsSortedByNameWithNameLike/";
        List<ProductDTO> expectedProductList = new ArrayList<>();
        List<ProductDTO> actualProductList = new ArrayList<>();

        for (Product product: products) {
            String name = "" + product.getName().charAt(ZERO);
            expectedProductList.add(
                    productConverter.convert(productRepository.findByNameContainingOrderByNameAsc(name).get(ZERO)));
            actualProductList.add(actualList(
                    mapping + name
                    , ProductDTO.class).get(ZERO));
        }

        assertThat(actualProductList.size(), is(greaterThan(ZERO)));
        assertThat(actualProductList, is(equalTo(expectedProductList)));
    }
}