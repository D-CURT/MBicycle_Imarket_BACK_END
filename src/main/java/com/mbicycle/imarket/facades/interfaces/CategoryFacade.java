package com.mbicycle.imarket.facades.interfaces;

import com.mbicycle.imarket.dto.CategoryDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CategoryFacade {
    boolean add(CategoryDTO categoryDTO);

    List<CategoryDTO> findByOrderByName();

    CategoryDTO get(String name);
}
