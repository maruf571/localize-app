package com.marufh.localizeapp.model.repository;

import com.marufh.localizeapp.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LanguageRepository extends JpaRepository<Language, String> {
    Language findByCode(String code);
}
