package com.marufh.localizeapp;

import com.marufh.localizeapp.model.Language;
import com.marufh.localizeapp.model.repository.LanguageRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner loadData(LanguageRepository repository) {
		return (args) -> {

			// Save language
			Language enExist = repository.findByCode("en");
			if(enExist == null) {
				Language enLanguage = new Language();
				enLanguage.setCode("en");
				enLanguage.setName("English");
				repository.save(enLanguage);
			}

			Language bnExist = repository.findByCode("bn");
			if(bnExist == null) {
				Language bnLanguage = new Language();
				bnLanguage.setCode("bn");
				bnLanguage.setName("Bengali");
				repository.save(bnLanguage);
			}
		};
	}
}
