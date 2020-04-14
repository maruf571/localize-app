package com.marufh.localizeapp.model.repository;

import com.marufh.localizeapp.model.ProductLocal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductLocalRepository extends JpaRepository<ProductLocal, String> {

    @Query("SELECT pl FROM ProductLocal pl " +
            "join fetch pl.product p " +
            "where p.id=?1 and pl.language.code = ?2")
    Optional<ProductLocal> findByProductAndLanguage(String productId, String lang);

    @Query("SELECT distinct pl FROM ProductLocal pl " +
            "where pl.language.code = ?1 ")
    Page<ProductLocal> findAll(String lang, Pageable pageable);
}
