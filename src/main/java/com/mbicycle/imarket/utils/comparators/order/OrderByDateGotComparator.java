package com.mbicycle.imarket.utils.comparators.order;

import com.mbicycle.imarket.beans.entities.Order;
import backup.annotations.EntityComparator;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

@EntityComparator
public class OrderByDateGotComparator implements Comparator<Order> {
    @Override
    public int compare(Order o1, Order o2) {
        Date o1Date = o1.getDateGot();
        Date o2Date = o2.getDateGot();
        return o1Date.compareTo(o2Date);
    }

    @Override
    public Comparator<Order> reversed() {
        return Collections.reverseOrder(this);
    }
}