package com.mbicycle.imarket.utils.comparators.product;

import com.mbicycle.imarket.beans.entities.Product;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Comparator;

@Component
public class ProductByDiscountComparator implements Comparator<Product> {

    @Override
    public int compare(Product o1, Product o2) {
        double o1Discount = Double.parseDouble(o1.getDiscount());
        double o2Discount = Double.parseDouble(o2.getDiscount());
        return Double.compare(o1Discount, o2Discount);
    }

    @Override
    public Comparator<Product> reversed() {
        return Collections.reverseOrder(this);
    }
}
