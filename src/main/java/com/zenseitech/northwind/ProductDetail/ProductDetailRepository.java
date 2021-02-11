package com.zenseitech.northwind.ProductDetail;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, Integer>, JpaSpecificationExecutor<ProductDetail> {

    Page<ProductDetail> findAll(Pageable pageable);
}
