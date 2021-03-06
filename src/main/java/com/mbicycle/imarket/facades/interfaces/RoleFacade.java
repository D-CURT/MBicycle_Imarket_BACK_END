package com.mbicycle.imarket.facades.interfaces;

import com.mbicycle.imarket.beans.dto.RoleDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RoleFacade {
    boolean add(RoleDTO dto);

    List<RoleDTO> findByOrderByRole();

    boolean delete(RoleDTO dto);
}
