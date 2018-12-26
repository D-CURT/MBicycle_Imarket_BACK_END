package com.mbicycle.imarket.dto;

import com.mbicycle.imarket.beans.entities.Group;
import com.mbicycle.imarket.beans.entities.OrderProduct;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

public class ProductDTO {

    private int id;

    private String name;

    private String descriptionPreview;

    private String descriptionFull;

    private double price;

    private MultipartFile picture;

    private boolean storeStatus;

    private int discount;

    private String group;

    private List<String> orderProducts;


    public ProductDTO() {
    }

    public ProductDTO(String name, String descriptionPreview, String descriptionFull, double price, MultipartFile picture, boolean storeStatus, int discount, String group, List<String> orderProducts) {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public MultipartFile getPicture() {
        return picture;
    }

    public void setPicture(MultipartFile picture) {
        this.picture = picture;
    }

    public boolean isStoreStatus() {
        return storeStatus;
    }

    public void setStoreStatus(boolean storeStatus) {
        this.storeStatus = storeStatus;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public List<String> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<String> orderProducts) {
        this.orderProducts = orderProducts;
    }

}
