package com.mbicycle.imarket.beans.entities;

import javax.persistence.*;

@Entity
@Table(name = "order_products")
public class OrderProduct {

    @Id
    @SequenceGenerator(name = "order_products_sequence_generator", sequenceName = "order_products_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_products_sequence_generator")
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_order")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;

    public OrderProduct() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public Product getProduct() {
        return product;
    }

    @Override
    public String toString() {
        return "OrderProduct{" +
                "id=" + id +
                ", order=" + order +
                ", product=" + product +
                '}';
    }
}
