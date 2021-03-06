package com.mbicycle.imarket.beans.dto;

import java.util.List;
import java.util.Objects;

public class ProductDTO {

    private Integer id;

    private String name;

    private String descriptionPreview;

    private String description;

    private Integer price;

    private String picture;

    private Boolean storeStatus;

    private Integer discount;

    private String group;

    private List<String> orderProducts;

    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ProductDTO() {
    }

    public ProductDTO(String name, String descriptionPreview, String descriptionFull, Integer price, String picture, Boolean storeStatus, Integer discount, String group, List<String> orderProducts) {
        setName(name);
        setDescriptionPreview(descriptionPreview);
        setDescription(descriptionFull);
        setPrice(price);
        setPicture(picture);
        setStoreStatus(storeStatus);
        setDiscount(discount);
        setGroup(group);
        setOrderProducts(orderProducts);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Boolean getStoreStatus() {
        return storeStatus;
    }

    public void setStoreStatus(Boolean storeStatus) {
        this.storeStatus = storeStatus;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDTO that = (ProductDTO) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
