package com.marufh.localizeapp.service.impl;

import com.marufh.localizeapp.exception.ProductException;
import com.marufh.localizeapp.model.Product;
import com.marufh.localizeapp.model.ProductLocal;
import com.marufh.localizeapp.model.repository.LanguageRepository;
import com.marufh.localizeapp.model.repository.ProductLocalRepository;
import com.marufh.localizeapp.model.repository.ProductRepository;
import com.marufh.localizeapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductLocalRepository productLocalRepository;

    private final LanguageRepository languageRepository;

    @Override
    public Product create(Product product) {
        //TODO:: Validate input those are not possible with annotation
        for(ProductLocal localization : product.getProductLocals().values()) {
            localization.setProduct(product);
            localization.setLanguage(languageRepository.findByCode(localization.getLanguage().getCode()));
        }
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) {
        // TODO:: Validate input those are not possible with annotation
        //TODO::  Get old product by id and update
        for(ProductLocal localization : product.getProductLocals().values()) {
            localization.setProduct(product);
        }
        return productRepository.save(product);
    }

    @Override
    public void delete(String id) {
        productRepository.deleteById(id);
    }

    @Override
    public ProductLocal findById(String id, String lang) {
        return productLocalRepository.findByProductAndLanguage(id, lang)
                .orElseThrow(
                    () -> new ProductException("Product not found", HttpStatus.NOT_FOUND)
                );
    }

    @Override
    public Page<ProductLocal> findAll(String lang, Pageable pageable) {
        //TODO:: Create custom query for search
        return productLocalRepository.findAll(lang, pageable);
    }
}
