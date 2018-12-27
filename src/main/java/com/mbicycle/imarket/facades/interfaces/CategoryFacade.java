package com.mbicycle.imarket.facades.interfaces;

import com.mbicycle.imarket.dto.CategoryDTO;
import org.springframework.stereotype.Component;

@Component
public interface CategoryFacade {
    boolean addCategory(CategoryDTO categoryDTO);
}
