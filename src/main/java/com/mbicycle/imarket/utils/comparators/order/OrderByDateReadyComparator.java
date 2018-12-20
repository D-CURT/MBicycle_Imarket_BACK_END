package com.mbicycle.imarket.utils.comparators.order;

import com.mbicycle.imarket.beans.entities.Order;
import com.mbicycle.imarket.utils.annotations.EntityComparator;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

@EntityComparator
public class OrderByDateReadyComparator implements Comparator<Order> {

    @Override
    public int compare(Order o1, Order o2) {
        Date o1Date = o1.getDate_ready();
        Date o2Date = o2.getDate_ready();
        return o1Date.compareTo(o2Date);
    }

    @Override
    public Comparator<Order> reversed() {
        return Collections.reverseOrder(this);
    }
}
