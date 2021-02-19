package com.zenseitech.northwind.productDetail;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductDetailController {

    Logger logger = LoggerFactory.getLogger(ProductDetailController.class);

    private ProductDetailService productDetailService;

    @Autowired
    public ProductDetailController(ProductDetailService productDetailService) {
        this.productDetailService = productDetailService;
    }

    @PostMapping(path = "/productdetails", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public RecordDomain searchProducts(@RequestParam MultiValueMap<String,String> paramMap) {

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
        recordDomain.setStatus("success");

        Pageable pageable = searchForm.getPageable();
        Page<ProductDetail> productDetailPage;

        if(searchForm.getSearch() == null) {
            productDetailPage = productDetailService.search(pageable);
        } else {
            ProductDetailSearch productDetailSearch = ProductDetailSearch.get(searchForm);
            productDetailPage = productDetailService.search(productDetailSearch, pageable);
        }

        recordDomain.setTotal(productDetailPage.getTotalElements());
        recordDomain.setRecords(List.class.cast(productDetailPage.getContent()));
        return recordDomain;
    }
}
