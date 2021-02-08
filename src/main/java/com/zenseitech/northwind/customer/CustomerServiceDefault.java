package com.zenseitech.northwind.customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public class CustomerServiceDefault implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceDefault(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Page<Customer> search(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public Page<Customer> search(Specification<Customer> specification, Pageable pageable) {
        return customerRepository.findAll(specification, pageable);
    }
}
