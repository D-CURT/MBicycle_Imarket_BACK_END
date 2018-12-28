package com.mbicycle.imarket.facades.interfaces;

import com.mbicycle.imarket.dto.UserDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserFacade {
    UserDTO get(UserDTO dto);

    boolean add(UserDTO dto);

    List<UserDTO> findByOrderByLogin();

    boolean delete(UserDTO dto);
}
