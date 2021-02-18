package com.zenseitech.northwind.orderdetail;

import com.zenseitech.northwind.util.RecordDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class OrderDetailController {

    Logger logger = LoggerFactory.getLogger(OrderDetailController.class);

    private OrderDetailService orderDetailService;

    @Autowired
    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @GetMapping("/orderDetail/{orderId}")
    public RecordDomain getOrderDetail(@PathVariable BigDecimal orderId) {
        // https://www.baeldung.com/spring-pathvariable
        List<OrderDetail> orderDetailList = orderDetailService.findByOrderId(orderId);
        RecordDomain recordDomain = new RecordDomain();
        recordDomain.setStatus("success");
        recordDomain.setRecords(List.class.cast(orderDetailList));
        recordDomain.setTotal(orderDetailList.size());
        return recordDomain;
    }
}
