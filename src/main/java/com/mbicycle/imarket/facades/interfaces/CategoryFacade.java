package com.mbicycle.imarket.facades.interfaces;

import com.mbicycle.imarket.beans.dto.CategoryDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CategoryFacade {
    boolean add(CategoryDTO categoryDTO);

    List<CategoryDTO> findByOrderByName();

    CategoryDTO get(String name);

    boolean delete(CategoryDTO dto);
}
