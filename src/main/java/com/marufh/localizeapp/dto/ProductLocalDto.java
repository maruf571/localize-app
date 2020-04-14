package com.marufh.localizeapp.dto;

import com.marufh.localizeapp.model.Language;
import com.marufh.localizeapp.model.Product;
import com.marufh.localizeapp.model.ProductLocal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductLocalDto {

    private String id;

    private String name;

    private String description;

    private Language language;

    private ProductDto product;

    public static ProductLocal convert(ProductLocalDto dto) {
        ProductLocal pl = new ProductLocal();
        pl.setId(dto.id);
        pl.setName(dto.name);
        pl.setDescription(dto.description);
        pl.setLanguage(dto.language);
        pl.setProduct(
                Optional.of(dto.product).map(productDto1 -> {
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
