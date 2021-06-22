package com.zenseitech.northwind.productDetail;

import com.zenseitech.northwind.customer.CustomerChanges;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductDetailService {
    Page<ProductDetail> search(Pageable pageable);
    Page<ProductDetail> search(ProductDetailSearch productDetailSearch, Pageable pageable);
    void update(List<ProductDetailsChanges> changesList);
}
