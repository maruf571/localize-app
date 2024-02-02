package com.marufh.localizeapp.service;

import com.marufh.localizeapp.dto.ProductDto;
import com.marufh.localizeapp.dto.ProductLocalDto;
import com.marufh.localizeapp.dto.ProductLocalMapper;
import com.marufh.localizeapp.dto.ProductMapper;
import com.marufh.localizeapp.exception.ProductException;
import com.marufh.localizeapp.model.Product;
import com.marufh.localizeapp.model.ProductLocal;
import com.marufh.localizeapp.model.repository.LanguageRepository;
import com.marufh.localizeapp.model.repository.ProductLocalRepository;
import com.marufh.localizeapp.model.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final LanguageRepository languageRepository;
    private final ProductLocalRepository productLocalRepository;


    public ProductDto create(ProductDto productDto) {
        Product product = ProductMapper.convert(productDto);
        for(ProductLocal localization : product.getProductLocals().values()) {
            localization.setProduct(product);
            localization.setLanguage(languageRepository.findByCode(localization.getLanguage().getCode()));
        }
        return ProductMapper.convert(productRepository.save(product));
    }


    public ProductDto update(ProductDto productDto) {
        Product product = ProductMapper.convert(productDto);
        for(ProductLocal localization : product.getProductLocals().values()) {
            localization.setProduct(product);
        }
        return ProductMapper.convert(
                productRepository.save(product)
        );
    }


    public void delete(String id) {
        productRepository.deleteById(id);
    }


    public ProductLocalDto findById(String id, String lang) {
        return productLocalRepository.findByProductAndLanguage(id, lang)
                .map(ProductLocalMapper::convert)
                .orElseThrow(
                    () -> new ProductException("Product not found", HttpStatus.NOT_FOUND)
                );
    }


    public Page<ProductLocalDto> findAll(String lang, Pageable pageable) {
        return productLocalRepository.findAll(lang, pageable)
                .map(ProductLocalMapper::convert);
    }
}
