package com.zenseitech.northwind.customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface CustomerService {
    Page<Customer> search(Pageable pageable);

    Page<Customer> search(Specification<Customer> specification, Pageable pageable);
}
