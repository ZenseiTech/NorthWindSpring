package com.zenseitech.northwind.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceDefault implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceDefault(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<Product> search(Pageable pageable) {
        return this.productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> search(Specification<Product> specification, Pageable pageable) {
        return this.productRepository.findAll(specification, pageable);
    }
}
