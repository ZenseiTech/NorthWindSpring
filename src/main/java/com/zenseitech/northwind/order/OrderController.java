package com.zenseitech.northwind.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class OrderController {

    Logger logger = LoggerFactory.getLogger(OrderController.class);

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(path = "/orders", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public RecordDomain searchOrders(@RequestParam MultiValueMap<String,String> paramMap,
                                       @RequestHeader Map<String, String> headers) {

        headers.forEach((key, value) -> {
            logger.info(String.format("Header '%s' = %s", key, value));
        });

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
        Pageable pageable = searchForm.getPageable();
        Page<Order> orderPage;
        recordDomain.setStatus("success");

        if(searchForm.getSearch() == null) {
            orderPage = orderService.search(pageable);
        } else {
            OrderSearch productSearch = OrderSearch.get(searchForm);
            orderPage = orderService.search(productSearch, pageable);
        }

        recordDomain.setTotal(orderPage.getTotalElements());
        recordDomain.setRecords(List.class.cast(orderPage.getContent()));
        return recordDomain;
    }
}
