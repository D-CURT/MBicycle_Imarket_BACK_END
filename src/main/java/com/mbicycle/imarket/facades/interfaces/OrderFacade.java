package com.mbicycle.imarket.facades.interfaces;

import com.mbicycle.imarket.beans.entities.Order;
import com.mbicycle.imarket.dto.OrderDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OrderFacade {
    boolean add(OrderDTO orderDTO);

    void update(OrderDTO orderDTO);

    boolean delete(OrderDTO orderDTO);

    List<OrderDTO> getAll();
}
