package com.zenseitech.northwind.ProductDetail;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductDetailService {
    Page<ProductDetail> search(Pageable pageable);
    Page<ProductDetail> search(ProductDetailSearch productDetailSearch, Pageable pageable);
}
