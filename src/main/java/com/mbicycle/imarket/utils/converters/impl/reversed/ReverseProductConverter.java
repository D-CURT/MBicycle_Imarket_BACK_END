package com.mbicycle.imarket.utils.converters.impl.reversed;

import com.mbicycle.imarket.beans.entities.Product;
import com.mbicycle.imarket.utils.converters.AbstractConverter;
import com.mbicycle.imarket.beans.dto.ProductDTO;
import com.mbicycle.imarket.services.interfaces.GroupService;
import org.springframework.beans.factory.annotation.Autowired;

public class ReverseProductConverter extends AbstractConverter<ProductDTO, Product> {

    @Autowired
    private GroupService groupService;

    @Override
    public void convert(ProductDTO source, Product target) {
        if(source.getId()!=null)
            target.setId(source.getId());
        target.setName(source.getName());
        target.setDescriptionPreview(source.getDescriptionPreview());
        target.setDescriptionFull(source.getDescription());
        target.setDiscount(source.getDiscount());
        target.setPicture(source.getPicture());
        target.setPrice(source.getPrice());
        target.setStoreStatus(source.getStoreStatus());
        target.setGroup(groupService.get(source.getGroup()));
    }
}

