package com.mbicycle.imarket.facades.interfaces;

import com.mbicycle.imarket.dto.ProfileDTO;
import org.springframework.stereotype.Component;

@Component
public interface ProfileFacade {
    boolean addProfile(ProfileDTO dto);
}
