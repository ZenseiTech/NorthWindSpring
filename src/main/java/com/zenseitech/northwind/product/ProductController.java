package com.zenseitech.northwind.product;

import com.zenseitech.northwind.util.RecordDomain;
import com.zenseitech.northwind.util.SearchForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    // https://www.baeldung.com/spring-boot-logging
    Logger logger = LoggerFactory.getLogger(ProductController.class);

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/products")
    public RecordDomain searchProducts(@RequestBody SearchForm searchForm) {
        logger.debug("====> " + searchForm.toString());
        RecordDomain recordDomain = new RecordDomain();
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
