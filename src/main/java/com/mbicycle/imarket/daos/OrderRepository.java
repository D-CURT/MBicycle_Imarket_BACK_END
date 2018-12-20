package com.mbicycle.imarket.daos;

import com.mbicycle.imarket.beans.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
