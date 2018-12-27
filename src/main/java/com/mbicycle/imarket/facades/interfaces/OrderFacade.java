package com.mbicycle.imarket.facades.interfaces;

import com.mbicycle.imarket.dto.OrderDTO;
import org.springframework.stereotype.Component;

@Component
public interface OrderFacade {
    void openOrder(OrderDTO orderDTO);

    void updateOrder(OrderDTO orderDTO);

    void deleteOrder(OrderDTO orderDTO);
}
