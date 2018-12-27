package com.mbicycle.imarket.facades.interfaces;

import com.mbicycle.imarket.dto.GroupDTO;
import org.springframework.stereotype.Component;

@Component
public interface GroupFacade {
    boolean addGroup(GroupDTO groupDTO);
}
