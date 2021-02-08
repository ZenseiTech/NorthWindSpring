package com.zenseitech.northwind.customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.text.MessageFormat;

public interface CustomerRepository extends JpaRepository<Customer, String>, JpaSpecificationExecutor<Customer> {

    Page<Customer> findAll(Pageable pageable);

    static Specification<Customer> countryContains(String expression) {
        return (root, query, builder) -> builder.like(root.get("country"), contains(expression));
    }

    static Specification<Customer> regionContains(String expression) {
        return (root, query, builder) -> builder.like(root.get("region"), contains(expression));
    }

    private static String contains(String expression) {
        return MessageFormat.format("%{0}%", expression);
    }

}
