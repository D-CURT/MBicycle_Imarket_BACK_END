package com.mbicycle.imarket.beans.entities;

import javax.persistence.*;

@Entity
@Table(name = "order_products")
public class OrderProduct {

    @Id
    @SequenceGenerator(name = "order_products_sequence_generator", sequenceName = "order_products_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_products_sequence_generator")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_order")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_product")
    private Product product;

    public OrderProduct() {
    }

    public OrderProduct(Order order, Product product) {
        setOrder(order);
        setProduct(product);
    }

    public int getId() {
        return id;
    }

    public final void setId(int id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public final void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public final void setProduct(Product product) {
        this.product = product;
    }

}
