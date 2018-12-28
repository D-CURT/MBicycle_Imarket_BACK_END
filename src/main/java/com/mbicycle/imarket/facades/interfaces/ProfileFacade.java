package com.mbicycle.imarket.facades.interfaces;

import com.mbicycle.imarket.dto.ProfileDTO;
import com.mbicycle.imarket.dto.UserDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProfileFacade {
    boolean add(ProfileDTO dto);

    boolean delete(ProfileDTO dto);

    ProfileDTO get(ProfileDTO dto);

    List<ProfileDTO> findByOrderByName();
}
