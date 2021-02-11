package com.zenseitech.northwind.product;

import com.zenseitech.northwind.util.Search;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {

    Page<Product> findAll(Pageable pageable);

    static Specification<Product> getSpecification(ProductSearch productSearch) {
        Specification<Object> specification = Specification
                .where(Search.getIntegerSpecification(productSearch.getIdValue(), productSearch.getIdField(), productSearch.getIdSearchType()))

                .and(Search.getStringSpecification(productSearch.getProductNameValue(), productSearch.getProductNameField(), productSearch.getProductNameSearchType()))

                .and(Search.getIntegerSpecification(productSearch.getSupplierIdValue(), productSearch.getSupplierIdField(), productSearch.getSupplierIdSearchType()))

                .and(Search.getIntegerSpecification(productSearch.getCategoryIdValue(), productSearch.getCategoryIdField(), productSearch.getCategoryIdSearchType()))

                .and(Search.getStringSpecification(productSearch.getQuantityPerUnitValue(), productSearch.getQuantityPerUnitField(), productSearch.getQuantityPerUnitSearchType()))

                .and(Search.getIntegerSpecification(productSearch.getUnitPriceValue(), productSearch.getUnitPriceField(), productSearch.getUnitPriceSearchType()))

                .and(Search.getIntegerSpecification(productSearch.getUnitsInStockValue(), productSearch.getUnitsInStockField(), productSearch.getUnitsInStockSearchType()))

                .and(Search.getIntegerSpecification(productSearch.getUnitsOnOrderValue(), productSearch.getUnitsOnOrderField(), productSearch.getUnitsOnOrderSearchType()))

                .and(Search.getIntegerSpecification(productSearch.getReorderLevelValue(), productSearch.getReorderLevelField(), productSearch.getReorderLevelSearchType()))

                .and(Search.getIntegerSpecification(productSearch.getDiscontinuedValue(), productSearch.getDiscontinuedField(), productSearch.getDiscontinuedSearchType()))
                ;

        return Specification.class.cast(specification);
    }
}
