package com.marufh.localizeapp.model.repository;

import com.marufh.localizeapp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String> {

    @Query("SELECT p from Product p " +
            "inner join p.productLocals pl " +
            "where p.id = ?1 AND pl.language.code = ?2 ")
    Optional<Product> findByIdAndLanguage(String productId, String languageCode);
}
