package com.zenseitech.northwind.orderdetail;

import java.math.BigDecimal;
import java.util.List;

public interface OrderDetailService {
    List<OrderDetail> findByOrderId(BigDecimal orderId);
}
