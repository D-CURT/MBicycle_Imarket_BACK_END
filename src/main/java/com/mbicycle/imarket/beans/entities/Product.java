package com.mbicycle.imarket.beans.entities;

import javax.persistence.*;

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

    private int price;

    private String picture;

    @Column(name = "store_status")
    private String storeStatus;

    private int discount;

    @ManyToOne
    @JoinColumn(name = "id_group", nullable = false)
    private Group group;

    public Product() {
    }

    public Product(String name, String descriptionPreview, String descriptionFull, int price, String picture, String storeStatus, int discount, Group group) {
        this.name = name;
        this.descriptionPreview = descriptionPreview;
        this.descriptionFull = descriptionFull;
        this.price = price;
        this.picture = picture;
        this.storeStatus = storeStatus;
        this.discount = discount;
        this.group = group;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptionPreview() {
        return descriptionPreview;
    }

    public void setDescriptionPreview(String descriptionPreview) {
        this.descriptionPreview = descriptionPreview;
    }

    public String getDescriptionFull() {
        return descriptionFull;
    }

    public void setDescriptionFull(String descriptionFull) {
        this.descriptionFull = descriptionFull;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getStoreStatus() {
        return storeStatus;
    }

    public void setStoreStatus(String storeStatus) {
        this.storeStatus = storeStatus;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", descriptionPreview='" + descriptionPreview + '\'' +
                ", descriptionFull='" + descriptionFull + '\'' +
                ", price=" + price +
                ", picture=" + picture +
                ", storeStatus='" + storeStatus + '\'' +
                ", discount='" + discount + '\'' +
            //    ", group=" + group +
                '}';
    }

}
