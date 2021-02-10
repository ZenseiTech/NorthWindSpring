package com.zenseitech.northwind.product;

import com.zenseitech.northwind.util.Search;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigDecimal;

public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {

    Page<Product> findAll(Pageable pageable);

    static Specification<Product> getSpecification(ProductSearch productSearch) {
        Product product = productSearch.getProduct();
        Specification<Object> specification = Specification
                .where(product.getId() == null ? null :
                        Search.getStringSpecification(product.getId(), productSearch.getIdField(), productSearch.getIdSearchType()))
                .and(product.getProductName() == null ? null :
                        Search.getStringSpecification(product.getProductName(), productSearch.getProductNameField(), productSearch.getProductNameSearchType()))
                .and(product.getSupplierId() < 0 ? null :
                        Search.getNumberSpecification(new BigDecimal(product.getSupplierId()), productSearch.getSupplierIdField(), productSearch.getSupplierIdSearchType()))
                .and(product.getCategoryId() < 0 ? null :
                        Search.getNumberSpecification(new BigDecimal(product.getCategoryId()), productSearch.getCategoryIdField(), productSearch.getCategoryIdSearchType()))
                .and(product.getQuantityPerUnit() == null ? null :
                        Search.getStringSpecification(product.getQuantityPerUnit(), productSearch.getQuantityPerUnitField(), productSearch.getQuantityPerUnitSearchType()))
                .and(product.getUnitPrice() == null ? null :
                        Search.getNumberSpecification(product.getUnitPrice(), productSearch.getUnitPriceField(), productSearch.getUnitPriceSearchType()))
                .and(product.getUnitsInStock() < 0 ? null :
                        Search.getNumberSpecification(new BigDecimal(product.getUnitsInStock()), productSearch.getUnitsInStockField(), productSearch.getUnitsInStockSearchType()))
                .and(product.getUnitsOnOrder() < 0 ? null :
                        Search.getNumberSpecification(new BigDecimal(product.getUnitsOnOrder()), productSearch.getUnitsOnOrderField(), productSearch.getUnitsOnOrderSearchType()))
                .and(product.getReorderLevel() < 0 ? null :
                        Search.getNumberSpecification(new BigDecimal(product.getReorderLevel()), productSearch.getReorderLevelField(), productSearch.getReorderLevelSearchType()))
                .and(product.getDiscontinued() < 0 ? null :
                        Search.getNumberSpecification(new BigDecimal(product.getDiscontinued()), productSearch.getDiscontinuedField(), productSearch.getDiscontinuedSearchType()))
                ;

        return Specification.class.cast(specification);
    }
}
