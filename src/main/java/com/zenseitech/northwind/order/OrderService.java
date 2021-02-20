package com.zenseitech.northwind.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    Page<OrderView> search(Pageable pageable);
    Page<OrderView> search(OrderSearch orderSearch, Pageable pageable);
}