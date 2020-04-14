package com.marufh.localizeapp.dto;

import com.marufh.localizeapp.model.Product;
import com.marufh.localizeapp.model.ProductLocal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private String id;

    private String name;

    private Map<String, ProductLocalDto> productLocals;

    public static Product convert(ProductDto dto) {
        Product product = new Product();
        product.setId(dto.id);
        product.setName(dto.name);
        product.setProductLocals(dto.productLocals.values().stream().map(plDto -> {
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

    public static List<ProductDto> convert(List<Product> products) {
        return null;
    }
}
