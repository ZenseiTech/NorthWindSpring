package com.zenseitech.northwind.orderdetail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigDecimal;
import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer>, JpaSpecificationExecutor<OrderDetail> {
    List<OrderDetail> findByOrderId(BigDecimal orderId);
}
