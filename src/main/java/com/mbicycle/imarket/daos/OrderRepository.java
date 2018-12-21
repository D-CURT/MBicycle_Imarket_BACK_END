package com.mbicycle.imarket.daos;

import com.mbicycle.imarket.beans.entities.Order;
import com.mbicycle.imarket.beans.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order findByProfile(Profile profile);
}
