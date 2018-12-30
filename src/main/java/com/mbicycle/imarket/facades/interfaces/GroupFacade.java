package com.mbicycle.imarket.facades.interfaces;

import com.mbicycle.imarket.beans.dto.GroupDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface GroupFacade {
    boolean add(GroupDTO groupDTO);

    GroupDTO get(String name);

    List<GroupDTO> findByOrderByName();

    boolean delete(GroupDTO dto);
}
