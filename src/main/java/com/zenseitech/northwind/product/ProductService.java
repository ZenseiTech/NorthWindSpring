package com.zenseitech.northwind.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Page<Product> search(Pageable pageable);
    Page<Product> search(ProductSearch productSearch, Pageable pageable);
}
