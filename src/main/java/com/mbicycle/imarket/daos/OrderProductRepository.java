package com.mbicycle.imarket.daos;

import com.mbicycle.imarket.beans.entities.Order;
import com.mbicycle.imarket.beans.entities.OrderProduct;
import com.mbicycle.imarket.beans.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Integer> {
    OrderProduct findByOrder(Order order);

    OrderProduct findByProduct(Product product);
}
