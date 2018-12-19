package com.mbicycle.imarket.utils.comparators.order;

import com.mbicycle.imarket.beans.entities.Order;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

@Component
public class OrderByDateOpenedComparator implements Comparator<Order> {

    @Override
    public int compare(Order o1, Order o2) {
        Date o1Date = o1.getDate_opened();
        Date o2Date = o2.getDate_opened();
        return o1Date.compareTo(o2Date);
    }

    @Override
    public Comparator<Order> reversed() {
        return Collections.reverseOrder(this);
    }
}
