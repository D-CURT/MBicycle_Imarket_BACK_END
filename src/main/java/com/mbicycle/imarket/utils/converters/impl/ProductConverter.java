package com.mbicycle.imarket.utils.converters.impl;

import com.mbicycle.imarket.beans.entities.Product;
import com.mbicycle.imarket.utils.converters.AbstractConverter;
import com.mbicycle.imarket.beans.dto.ProductDTO;

public class ProductConverter extends AbstractConverter<Product, ProductDTO> {

    @Override
    public void convert(Product source, ProductDTO target) {
        target.setId(source.getId());
        target.setName(source.getName());
        target.setDescriptionPreview(source.getDescriptionPreview());
        target.setDescription(source.getDescriptionFull());
        target.setDiscount(source.getDiscount());
        target.setPicture(source.getPicture());
        target.setPrice(source.getPrice());
        target.setStoreStatus(source.getStoreStatus());
        target.setGroup(source.getGroup().getName());
        target.setCategory(source.getGroup().getCategory().getName());
    }
}

