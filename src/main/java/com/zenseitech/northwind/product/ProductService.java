package com.zenseitech.northwind.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface ProductService {
    Page<Product> search(Pageable pageable);

    Page<Product> search(Specification<Product> specification, Pageable pageable);
}
