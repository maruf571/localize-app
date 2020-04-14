package com.marufh.localizeapp.service;

import com.marufh.localizeapp.model.Product;
import com.marufh.localizeapp.model.ProductLocal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    Product create(Product product);

    Product update(Product product);

    void delete(String id);

    ProductLocal findById(String id, String lang);

    Page<ProductLocal> findAll(String lang, Pageable pageable);
}
