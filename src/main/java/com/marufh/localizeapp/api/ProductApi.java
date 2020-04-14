package com.marufh.localizeapp.api;

import com.marufh.localizeapp.dto.ProductDto;
import com.marufh.localizeapp.model.ProductLocal;
import com.marufh.localizeapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ProductApi.URL)
public class ProductApi {

    public static final String URL = "/api/products";
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDto> create(@RequestBody ProductDto productDto) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(
                            ProductDto.convert(
                                    productService.create(
                                            ProductDto.convert(productDto)
                                    )
                            )
                    );
    }

    @PutMapping
    public ResponseEntity<ProductDto> update(@RequestBody ProductDto productDto) {
        return ResponseEntity.ok(
                ProductDto.convert(
                        productService.update(
                                ProductDto.convert(productDto)
                        )
                )
        );
    }

    @DeleteMapping("/id/{id}")
    public void delete(@PathVariable  String id) {
        productService.delete(id);
    }

    @GetMapping("/id/{id}/lang/{lang}")
    public ResponseEntity<ProductLocal> findById(@PathVariable  String id, @PathVariable String lang) {
        return ResponseEntity.ok(productService.findById(id, lang));
    }

    @GetMapping("/lang/{lang}")
    public ResponseEntity<Page<ProductLocal>> findAll(@PathVariable String lang, Pageable pageable) {
        return ResponseEntity.ok(productService.findAll(lang, pageable));
    }
}
