package com.zenseitech.northwind.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    Page<Order> search(Pageable pageable);
    Page<Order> search(OrderSearch orderSearch, Pageable pageable);
}