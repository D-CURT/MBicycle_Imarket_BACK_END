package com.mbicycle.imarket.facades;

import com.mbicycle.imarket.dto.OrderDTO;
import org.springframework.stereotype.Component;

@Component
public interface OrderFacade {
    public void openOrder(OrderDTO orderDTO);
    public void updateOrder(OrderDTO orderDTO);
    public void deleteOrder(OrderDTO orderDTO);
}
