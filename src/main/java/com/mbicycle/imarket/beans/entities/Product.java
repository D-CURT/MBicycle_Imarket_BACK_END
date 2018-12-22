package com.mbicycle.imarket.beans.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @SequenceGenerator(name = "products_sequence_generator", sequenceName = "products_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "products_sequence_generator")
    private int id;

    private String name;

    @Column(name = "description_preview")
    private String descriptionPreview;

    @Column(name = "description_full")
    private String descriptionFull;

    private double price;

    private String picture;

    @Column(name = "store_status")
    private boolean storeStatus;

    private int discount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_group", nullable = false)
    private Group group;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<OrderProduct> orderProducts;

    public Product() {
    }

    public Product(String name, String descriptionPreview, String descriptionFull, double price, String picture, boolean storeStatus, int discount, Group group, List<OrderProduct> orderProducts) {
        setName(name);
        setDescriptionPreview(descriptionPreview);
        setDescriptionFull(descriptionFull);
        setPrice(price);
        setPicture(picture);
        setStoreStatus(storeStatus);
        setDiscount(discount);
        setGroup(group);
        setOrderProducts(orderProducts);
    }

    public int getId() {
        return id;
    }

    public final void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public final void setName(String name) {
        this.name = name;
    }

    public String getDescriptionPreview() {
        return descriptionPreview;
    }

    public final void setDescriptionPreview(String descriptionPreview) {
        this.descriptionPreview = descriptionPreview;
    }

    public String getDescriptionFull() {
        return descriptionFull;
    }

    public final void setDescriptionFull(String descriptionFull) {
        this.descriptionFull = descriptionFull;
    }

    public double getPrice() {
        return price;
    }

    public final void setPrice(double price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public final void setPicture(String picture) {
        this.picture = picture;
    }

    public boolean isStoreStatus() {
        return storeStatus;
    }

    public final void setStoreStatus(boolean storeStatus) {
        this.storeStatus = storeStatus;
    }

    public int getDiscount() {
        return discount;
    }

    public final void setDiscount(int discount) {
        this.discount = discount;
    }

    public Group getGroup() {
        return group;
    }

    public final void setGroup(Group group) {
        this.group = group;
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public final void setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }
}
