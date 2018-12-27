package com.mbicycle.imarket.converters;

import com.mbicycle.imarket.beans.entities.Product;
import com.mbicycle.imarket.dto.ProductDTO;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
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
        target.setStoreStatus(source.isStoreStatus());

        target.setGroup(source.getGroup().getName());
        target.setCategory(source.getGroup().getCategory().getName());
        //FIXME: 2 fields, group and category. Must be correct conversion. (test?)

        //Example with Streams:
            //        target.setRoles(source.getAuthorities()
            //                .stream()
            //                .map(authority -> authority.getAuthorityType().getRole())
            //                .collect(Collectors.toList()));

    }
}

