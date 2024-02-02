package com.marufh.localizeapp;

import com.marufh.localizeapp.model.Language;
import com.marufh.localizeapp.model.repository.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
@RequiredArgsConstructor
public class InitData implements CommandLineRunner {

    private final LanguageRepository languageRepository;

    @Override
    public void run(String... args) throws Exception {

        // Save language
        Language enExist = languageRepository.findByCode("en");
        if(enExist == null) {
            Language enLanguage = new Language();
            enLanguage.setCode("en");
            enLanguage.setName("English");
            languageRepository.save(enLanguage);
        }

        Language bnExist = languageRepository.findByCode("bn");
        if(bnExist == null) {
            Language bnLanguage = new Language();
            bnLanguage.setCode("bn");
            bnLanguage.setName("Bengali");
            languageRepository.save(bnLanguage);
        }
    }
}
