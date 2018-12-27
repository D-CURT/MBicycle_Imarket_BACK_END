package com.mbicycle.imarket.facades.interfaces;

import com.mbicycle.imarket.dto.UserDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserFacade {
    boolean addUser(UserDTO dto);

    List<UserDTO> findByOrderByLogin();
}
