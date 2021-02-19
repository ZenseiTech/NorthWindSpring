package com.zenseitech.northwind.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zenseitech.northwind.util.RecordDomain;
import com.zenseitech.northwind.util.SearchForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ProductController {

    // https://www.baeldung.com/spring-boot-logging
    Logger logger = LoggerFactory.getLogger(ProductController.class);

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(path = "/products", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public RecordDomain searchProducts(@RequestParam MultiValueMap<String,String> paramMap,
                                       @RequestHeader Map<String, String> headers) {

        RecordDomain recordDomain = new RecordDomain();
        SearchForm searchForm;

        try {
            searchForm = SearchForm.get(paramMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            recordDomain.setStatus("error");
            return recordDomain;
        }

        logger.debug("====> " + searchForm.toString());

        // https://www.baeldung.com/spring-rest-http-headershtt
        headers.forEach((key, value) -> {
            logger.info(String.format("Header '%s' = %s", key, value));
        });

        recordDomain.setStatus("success");

        Pageable pageable = searchForm.getPageable();
        Page<Product> productPage;

        if(searchForm.getSearch() == null) {
            productPage = productService.search(pageable);
        } else {
            ProductSearch productSearch = ProductSearch.get(searchForm);
            productPage = productService.search(productSearch, pageable);
        }

        recordDomain.setTotal(productPage.getTotalElements());
        recordDomain.setRecords(List.class.cast(productPage.getContent()));
        return recordDomain;
    }
}
