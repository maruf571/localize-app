package com.marufh.localizeapp.dto;

import com.marufh.localizeapp.model.Product;
import com.marufh.localizeapp.model.ProductLocal;

import java.util.Optional;

public class ProductLocalMapper {

    public static ProductLocal convert(ProductLocalDto dto) {
        ProductLocal pl = new ProductLocal();
        pl.setId(dto.getId());
        pl.setName(dto.getName());
        pl.setDescription(dto.getDescription());
        pl.setLanguage(dto.getLanguage());
        pl.setProduct(
                Optional.of(dto.getProduct()).map(productDto1 -> {
                    Product product = new Product();
                    product.setId(productDto1.getId());
                    return product;
                }).get());
        return pl;
    }

    public static ProductLocalDto convert(ProductLocal productLocal) {
        return ProductLocalDto.builder()
                .id(productLocal.getId())
                .name(productLocal.getName())
                .description(productLocal.getDescription())
                .language(productLocal.getLanguage())
                .product(Optional.of(productLocal.getProduct()).map(product1 ->
                        ProductDto.builder()
                                .id(product1.getId())
                                .build()).get()
                )
                .build();
    }
}
