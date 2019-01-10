package com.mbicycle.imarket.facades.interfaces;


import com.mbicycle.imarket.beans.dto.OrderDTO;
import com.mbicycle.imarket.beans.dto.ProductDTO;
import com.mbicycle.imarket.beans.dto.ProfileDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OrderFacade {

    boolean update(OrderDTO orderDTO);

    boolean managing_update(OrderDTO orderDTO);

    boolean delete(OrderDTO orderDTO);

    List<OrderDTO> getAll();

    List<OrderDTO> get(ProfileDTO profileDTO);

    boolean cart_add(OrderDTO orderDTO);

    boolean cart_deleteProduct(OrderDTO dto);

    List<ProductDTO> cart_getProducts(OrderDTO dto);
}
