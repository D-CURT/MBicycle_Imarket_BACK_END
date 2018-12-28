package com.mbicycle.imarket.facades.interfaces;

import com.mbicycle.imarket.beans.entities.User;
import com.mbicycle.imarket.dto.UserDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserFacade {
    boolean add(UserDTO dto);

    List<UserDTO> findByOrderByLogin();

    boolean delete(UserDTO dto);
}
