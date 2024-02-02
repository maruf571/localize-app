package com.marufh.localizeapp.dto;

import com.marufh.localizeapp.model.Product;
import com.marufh.localizeapp.model.ProductLocal;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


public class ProductMapper {

    public static Product convert(ProductDto dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setProductLocals(dto.getProductLocals().values().stream().map(plDto -> {
                    ProductLocal pl = new ProductLocal();
                    pl.setId(plDto.getId());
                    pl.setName(plDto.getName());
                    pl.setDescription(plDto.getDescription());
                    pl.setLanguage(plDto.getLanguage());
                    return pl;
                }).collect(Collectors.toMap(pl -> pl.getLanguage().getCode(), pl -> pl))
        );
        return product;
    }

    public static ProductDto convert(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .productLocals(product.getProductLocals().values().stream().map(productLocal ->
                                ProductLocalDto.builder()
                                        .id(productLocal.getId())
                                        .name(productLocal.getName())
                                        .description(productLocal.getDescription())
                                        .language(productLocal.getLanguage())
                                        .build()
                        ).collect(Collectors.toMap(pld -> pld.getLanguage().getCode(), pld -> pld))
                ).build();
    }
}
