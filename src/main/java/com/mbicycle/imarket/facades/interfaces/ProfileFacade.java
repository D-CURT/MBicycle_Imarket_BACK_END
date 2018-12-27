package com.mbicycle.imarket.facades.interfaces;

import com.mbicycle.imarket.dto.ProfileDTO;
import com.mbicycle.imarket.dto.UserDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProfileFacade {
    boolean addProfile(ProfileDTO dto);

    void delete(UserDTO userDTO);

    ProfileDTO findByUser(UserDTO userDTO);

    List<ProfileDTO> findByOrderByName();
}
