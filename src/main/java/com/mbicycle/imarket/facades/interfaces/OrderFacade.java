package com.mbicycle.imarket.facades.interfaces;

import com.mbicycle.imarket.dto.OrderDTO;
import com.mbicycle.imarket.dto.ProfileDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OrderFacade {
    boolean add(OrderDTO orderDTO);

    boolean update(OrderDTO orderDTO);

    boolean delete(OrderDTO orderDTO);

    List<OrderDTO> getAll();

    OrderDTO get(ProfileDTO profileDTO);
}
