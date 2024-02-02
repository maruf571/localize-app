package com.marufh.localizeapp.controller;

import com.marufh.localizeapp.dto.ProductDto;
import com.marufh.localizeapp.dto.ProductLocalDto;
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
@RequestMapping(ProductController.URL)
public class ProductController {

    public static final String URL = "/api/products";
    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto create(@RequestBody ProductDto productDto) {
            return productService.create(productDto);
    }

    @PutMapping
    public ProductDto update(@RequestBody ProductDto productDto) {
        return productService.update(productDto);

    }

    @DeleteMapping("/id/{id}")
    public void delete(@PathVariable  String id) {
        productService.delete(id);
    }

    @GetMapping("/id/{id}/lang/{lang}")
    public ProductLocalDto findById(@PathVariable  String id, @PathVariable String lang) {
        return productService.findById(id, lang);
    }

    @GetMapping("/lang/{lang}")
    public Page<ProductLocalDto> findAll(@PathVariable String lang, Pageable pageable) {
        return productService.findAll(lang, pageable);
    }
}
