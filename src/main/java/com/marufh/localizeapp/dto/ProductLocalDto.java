package com.marufh.localizeapp.dto;

import com.marufh.localizeapp.model.Language;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
