package com.zenseitech.northwind.customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, String> {

    Page<Customer> findByCountry(String country, Pageable pageable);

    Page<Customer> findAll(Pageable pageable);

    @Query("select count(id) from Customer c where c.country = ?1")
    long countByCountry(String country);

}
