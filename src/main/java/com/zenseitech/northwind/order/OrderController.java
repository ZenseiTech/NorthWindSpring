package com.zenseitech.northwind.order;

import com.zenseitech.northwind.util.RecordDomain;
import com.zenseitech.northwind.util.SearchForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/orders")
    public RecordDomain searchOrders(@RequestBody SearchForm searchForm,
                                       @RequestHeader Map<String, String> headers) {
        logger.debug("====> " + searchForm.toString());

        headers.forEach((key, value) -> {
            logger.info(String.format("Header '%s' = %s", key, value));
        });

        RecordDomain recordDomain = new RecordDomain();
        recordDomain.setStatus("success");

        Pageable pageable = searchForm.getPageable();
        Page<Order> orderPage;

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
