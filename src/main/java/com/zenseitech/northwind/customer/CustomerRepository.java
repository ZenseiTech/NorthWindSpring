package com.zenseitech.northwind.customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, String> {
    Page<Customer> findByCountry(String country, Pageable pageable);
}
