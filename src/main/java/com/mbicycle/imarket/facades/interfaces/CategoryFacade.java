package com.mbicycle.imarket.facades.interfaces;

import com.mbicycle.imarket.dto.CategoryDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CategoryFacade {
    boolean addCategory(CategoryDTO categoryDTO);

    List<CategoryDTO> findByOrderByName();

    CategoryDTO getCategoryDTO(String name);
}
