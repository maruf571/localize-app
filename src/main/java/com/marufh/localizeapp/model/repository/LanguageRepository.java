package com.marufh.localizeapp.model.repository;

import com.marufh.localizeapp.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LanguageRepository extends JpaRepository<Language, String> {

    @Query("SELECT l FROM Language l where l.code = ?1")
    Language findByCode(String code);
}
