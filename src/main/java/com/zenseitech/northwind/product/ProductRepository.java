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
//        Product product = productSearch.getProduct();
        Specification<Object> specification = Specification
                .where(productSearch.getIdField() == null ? null :
                        Search.getIntegerSpecification(productSearch.getIdValue(), productSearch.getIdField(), productSearch.getIdSearchType()))

                .and(productSearch.getProductNameField() == null ? null :
                        Search.getStringSpecification(productSearch.getProductNameValue(), productSearch.getProductNameField(), productSearch.getProductNameSearchType()))

                .and(productSearch.getSupplierIdField() == null ? null :
                        Search.getIntegerSpecification(productSearch.getSupplierIdValue(), productSearch.getSupplierIdField(), productSearch.getSupplierIdSearchType()))

                .and(productSearch.getCategoryIdField() == null ? null :
                        Search.getIntegerSpecification(productSearch.getCategoryIdValue(), productSearch.getCategoryIdField(), productSearch.getCategoryIdSearchType()))

                .and(productSearch.getQuantityPerUnitField() == null ? null :
                        Search.getStringSpecification(productSearch.getQuantityPerUnitValue(), productSearch.getQuantityPerUnitField(), productSearch.getQuantityPerUnitSearchType()))

                .and(productSearch.getUnitPriceField() == null ? null :
                        Search.getIntegerSpecification(productSearch.getUnitPriceValue(), productSearch.getUnitPriceField(), productSearch.getUnitPriceSearchType()))

                .and(productSearch.getUnitsInStockField() == null ? null :
                        Search.getIntegerSpecification(productSearch.getUnitsInStockValue(), productSearch.getUnitsInStockField(), productSearch.getUnitsInStockSearchType()))

                .and(productSearch.getUnitsOnOrderField() == null ? null :
                        Search.getIntegerSpecification(productSearch.getUnitsOnOrderValue(), productSearch.getUnitsOnOrderField(), productSearch.getUnitsOnOrderSearchType()))

                .and(productSearch.getReorderLevelField() == null ? null :
                        Search.getIntegerSpecification(productSearch.getReorderLevelValue(), productSearch.getReorderLevelField(), productSearch.getReorderLevelSearchType()))

                .and(productSearch.getDiscontinuedField() == null ? null :
                        Search.getIntegerSpecification(productSearch.getDiscontinuedValue(), productSearch.getDiscontinuedField(), productSearch.getDiscontinuedSearchType()))
                ;

        return Specification.class.cast(specification);
    }
}
