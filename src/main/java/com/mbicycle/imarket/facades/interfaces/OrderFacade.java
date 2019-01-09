package com.mbicycle.imarket.facades.interfaces;


import com.mbicycle.imarket.beans.dto.OrderDTO;
import com.mbicycle.imarket.beans.dto.ProductDTO;
import com.mbicycle.imarket.beans.dto.ProfileDTO;
import com.mbicycle.imarket.beans.entities.Order;
import com.mbicycle.imarket.beans.entities.Product;
import com.mbicycle.imarket.beans.entities.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OrderFacade {
    boolean add(OrderDTO orderDTO);

    boolean update(OrderDTO orderDTO);

    boolean delete(OrderDTO orderDTO);

    boolean deleteProduct(OrderDTO dto);

    List<OrderDTO> getAll();

    List<OrderDTO> get(ProfileDTO profileDTO);

    List<ProductDTO> getProducts(OrderDTO dto);
}
