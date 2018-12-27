package com.mbicycle.imarket.facades.interfaces;

import com.mbicycle.imarket.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public interface UserFacade {
    boolean addUser(UserDTO dto);
}
