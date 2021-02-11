package com.zenseitech.northwind.customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {
    Page<Customer> search(Pageable pageable);

    Page<Customer> search(CustomerSearch customerSearch, Pageable pageable);
}
