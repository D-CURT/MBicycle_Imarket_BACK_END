package com.mbicycle.imarket.utils.comparators.product;

import com.mbicycle.imarket.beans.entities.Product;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Comparator;

@Component
public class ProductByPriceComparator implements Comparator<Product> {

    @Override
    public int compare(Product o1, Product o2) {
        double o1Price = Double.parseDouble(o1.getPrice());
        double o2Price = Double.parseDouble(o2.getPrice());
        return Double.compare(o1Price, o2Price);
    }

    @Override
    public Comparator<Product> reversed() {
        return Collections.reverseOrder(this);
    }
}
