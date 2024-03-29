package com.marufh.localizeapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marufh.localizeapp.dto.ProductDto;
import com.marufh.localizeapp.dto.ProductLocalDto;
import com.marufh.localizeapp.model.Language;
import com.marufh.localizeapp.model.repository.LanguageRepository;
import com.marufh.localizeapp.model.repository.ProductLocalRepository;
import com.marufh.localizeapp.model.repository.ProductRepository;
import com.marufh.localizeapp.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ProductControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ProductService productService;

    @Autowired
    LanguageRepository languageRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductLocalRepository productLocalRepository;

    private static  ObjectMapper objectMapper = new ObjectMapper();


    @Test
    @Transactional
    void should_create_product() throws Exception {

        // Given
        Language en = new Language();
        en.setCode("en");
        en.setName("English");
        languageRepository.save(en);

        Language bn = new Language();
        bn.setCode("bn");
        bn.setName("Bengali");
        languageRepository.save(bn);


        // Localize product en
        ProductLocalDto enProduct = new ProductLocalDto();
        enProduct.setName("product1");
        enProduct.setDescription("Description1");
        enProduct.setLanguage(en);

        // Localize product bn
        ProductLocalDto bnProduct = new ProductLocalDto();
        bnProduct.setName("পণ্য ১");
        bnProduct.setDescription("বর্ননা ১");
        bnProduct.setLanguage(bn);

        // Localize product map
        Map<String, ProductLocalDto> productLocals = new HashMap<>();
        productLocals.put("en", enProduct);
        productLocals.put("bn", bnProduct);

        ProductDto product = new ProductDto();
        product.setName("This is product");
        product.setProductLocals(productLocals);

        // Then
        this.mockMvc.perform(MockMvcRequestBuilders.post(ProductController.URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.productLocals.length()").value(2))

                // Inspect language
                .andExpect(jsonPath("$.productLocals['en'].language.code").value("en"))
                .andExpect(jsonPath("$.productLocals['bn'].language.code").value("bn"))

                // Inspect localize product
                .andExpect(jsonPath("$.productLocals['en'].name").value("product1"))
                .andExpect(jsonPath("$.productLocals['en'].description").value("Description1"))

                .andExpect(jsonPath("$.productLocals['bn'].name").value("পণ্য ১"))
                .andExpect(jsonPath("$.productLocals['bn'].description").value("বর্ননা ১"))
        ;

    }

    @Test
    void should_find_by_id() throws Exception {

        // Given
        Language en = new Language();
        en.setCode("en");
        en.setName("English");
        languageRepository.save(en);

        Language bn = new Language();
        bn.setCode("bn");
        bn.setName("Bengali");
        languageRepository.save(bn);


        // Localize product en
        ProductLocalDto enProduct = new ProductLocalDto();
        enProduct.setName("product1");
        enProduct.setDescription("Description1");
        enProduct.setLanguage(en);

        // Localize product bn
        ProductLocalDto bnProduct = new ProductLocalDto();
        bnProduct.setName("পণ্য১");
        bnProduct.setDescription("বর্ননা১");
        bnProduct.setLanguage(bn);

        // Localize product map
        Map<String, ProductLocalDto> localizations = new HashMap<>();
        localizations.put("en", enProduct);
        localizations.put("bn", bnProduct);

        ProductDto product = new ProductDto();
        product.setName("This is product");
        product.setProductLocals(localizations);

        ProductDto createdProduct  = productService.create(product);

        // Then
        this.mockMvc.perform(get(ProductController.URL + "/id/" +  createdProduct.getId()+"/lang/en")
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())

                // Inspect language
                .andExpect(jsonPath("$.language.code").value("en"))

                // Inspect localize product
                .andExpect(jsonPath("$.name").value("product1"))
                .andExpect(jsonPath("$.description").value("Description1"))
           ;

        // Clean up as @Transaction not working
        productService.delete(createdProduct.getId());
        languageRepository.delete(en);
        languageRepository.delete(bn);

    }


    @Test
    void should_find_all() throws Exception {

        // Given
        Language en = new Language();
        en.setCode("en");
        en.setName("English");
        languageRepository.save(en);

        Language bn = new Language();
        bn.setCode("bn");
        bn.setName("Bengali");
        languageRepository.save(bn);


        // Localize product en
        ProductLocalDto enProduct = new ProductLocalDto();
        enProduct.setName("product1");
        enProduct.setDescription("Description1");
        enProduct.setLanguage(en);

        // Localize product bn
        ProductLocalDto bnProduct = new ProductLocalDto();
        bnProduct.setName("পণ্য১");
        bnProduct.setDescription("বর্ননা১");
        bnProduct.setLanguage(bn);

        // Localize product map
        Map<String, ProductLocalDto> localizations = new HashMap<>();
        localizations.put("en", enProduct);
        localizations.put("bn", bnProduct);

        ProductDto product = new ProductDto();
        product.setName("This is product");
        product.setProductLocals(localizations);

        ProductDto createdProduct1 = productService.create(product);



        // Localize product en
        ProductLocalDto enProduct2 = new ProductLocalDto();
        enProduct2.setName("product2");
        enProduct2.setDescription("Description2");
        enProduct2.setLanguage(en);

        // Localize product bn
        ProductLocalDto bnProduct2 = new ProductLocalDto();
        bnProduct2.setName("পণ্য২");
        bnProduct2.setDescription("বর্ননা২");
        bnProduct2.setLanguage(bn);

        // Localize product map
        Map<String, ProductLocalDto> productLocals2 = new HashMap<>();
        productLocals2.put("en", enProduct2);
        productLocals2.put("bn", bnProduct2);

        ProductDto product2 = new ProductDto();
        product2.setName("This is product2");
        product2.setProductLocals(productLocals2);


        ProductDto createdProduct2 = productService.create(product2);

        // Then
        this.mockMvc.perform(get(ProductController.URL + "/lang/en")
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.content.length()").value(2))
        ;

        // Then
        this.mockMvc.perform(get(ProductController.URL + "/lang/bn")
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.content.length()").value(2))
        ;

        // Cleanup as @Transactional not working
        productService.delete(createdProduct1.getId());
        productService.delete(createdProduct2.getId());
        languageRepository.delete(en);
        languageRepository.delete(bn);

    }

    @Test
    @Transactional
    void should_update() throws Exception {

        // Given
        Language en = new Language();
        en.setCode("en");
        en.setName("English");
        languageRepository.save(en);

        Language bn = new Language();
        bn.setCode("bn");
        bn.setName("Bengali");
        languageRepository.save(bn);


        // Localize product en
        ProductLocalDto enProduct = new ProductLocalDto();
        enProduct.setName("product1");
        enProduct.setDescription("Description1");
        enProduct.setLanguage(en);

        // Localize product bn
        ProductLocalDto bnProduct = new ProductLocalDto();
        bnProduct.setName("পণ্য১");
        bnProduct.setDescription("বর্ননা১");
        bnProduct.setLanguage(bn);

        // Localize product map
        Map<String, ProductLocalDto> localizations = new HashMap<>();
        localizations.put("en", enProduct);
        localizations.put("bn", bnProduct);

        ProductDto product = new ProductDto();
        product.setName("This is product");
        product.setProductLocals(localizations);

        ProductDto createdProduct = productService.create(product);

        createdProduct.setName("update name");
        createdProduct.getProductLocals().get("en").setName("update product1");
        createdProduct.getProductLocals().get("bn").setName("হালনাগাদ নাম");


        this.mockMvc.perform(put(ProductController.URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createdProduct))
        )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.name").value("update name"))
                .andExpect(jsonPath("$.productLocals['en'].name").value("update product1"))
                .andExpect(jsonPath("$.productLocals['bn'].name").value("হালনাগাদ নাম"))

        ;
    }


    @Test
    @Transactional
    void should_delete() throws Exception {

        // Given
        Language en = new Language();
        en.setCode("en");
        en.setName("English");
        languageRepository.save(en);

        Language bn = new Language();
        bn.setCode("bn");
        bn.setName("Bengali");
        languageRepository.save(bn);


        // Localize product en
        ProductLocalDto enProduct = new ProductLocalDto();
        enProduct.setName("product1");
        enProduct.setDescription("Description1");
        enProduct.setLanguage(en);

        // Localize product bn
        ProductLocalDto bnProduct = new ProductLocalDto();
        bnProduct.setName("পণ্য১");
        bnProduct.setDescription("বর্ননা১");
        bnProduct.setLanguage(bn);

        // Localize product map
        Map<String, ProductLocalDto> localizations = new HashMap<>();
        localizations.put("en", enProduct);
        localizations.put("bn", bnProduct);

        ProductDto product = new ProductDto();
        product.setName("This is product");
        product.setProductLocals(localizations);

        ProductDto createdProduct = productService.create(product);


        this.mockMvc.perform(delete(ProductController.URL + "/id/" + createdProduct.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createdProduct))
        )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
        ;
    }

}
