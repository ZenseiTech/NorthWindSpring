package com.zenseitech.northwind.productDetail;

import com.zenseitech.northwind.customer.CustomerServiceDefault;
import com.zenseitech.northwind.util.Search;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDetailServiceDefault implements ProductDetailService {

    private final ProductDetailRepository productDetailRepository;

    Logger logger = LoggerFactory.getLogger(CustomerServiceDefault.class);

    @Autowired
    public ProductDetailServiceDefault(ProductDetailRepository productDetailRepository) {
        this.productDetailRepository = productDetailRepository;
    }

    @Override
    public Page<ProductDetail> search(Pageable pageable) {
        return this.productDetailRepository.findAll(pageable);
    }

    @Override
    public Page<ProductDetail> search(ProductDetailSearch productDetailSearch, Pageable pageable) {
        Specification<ProductDetail> specification = getSpecification(productDetailSearch);
        return this.productDetailRepository.findAll(specification, pageable);
    }

    @Override
    public void update(List<ProductDetailsChanges> changesList) {
        ProductDetail productDetail = productDetailRepository.findById(Integer.valueOf(changesList.get(0).getRecid())).orElse(null);
        if(productDetail != null) {
            for(ProductDetailsChanges changes : changesList) {
                changes.update(productDetail);
                productDetailRepository.save(productDetail);
            }
        } else {
            logger.debug("Customer not found: " + changesList.get(0).getRecid());
            return;
        }
    }

    protected Specification<ProductDetail> getSpecification(ProductDetailSearch productDetailSearch) {
        Specification<Object> specification = Specification
                .where(Search.getIntegerSpecification(productDetailSearch.getIdValue(), productDetailSearch.getIdField(), productDetailSearch.getIdSearchType()))

                .and(Search.getStringSpecification(productDetailSearch.getProductNameValue(), productDetailSearch.getProductNameField(), productDetailSearch.getProductNameSearchType()))

                .and(Search.getStringSpecification(productDetailSearch.getSupplierNameValue(), productDetailSearch.getSupplierNameField(), productDetailSearch.getSupplierNameSearchType()))

                .and(Search.getStringSpecification(productDetailSearch.getSupplierRegionValue(), productDetailSearch.getSupplierRegionField(), productDetailSearch.getSupplierRegionSearchType()))

                .and(Search.getStringSpecification(productDetailSearch.getCategoryNameValue(), productDetailSearch.getCategoryNameField(), productDetailSearch.getCategoryNameSearchType()))

                .and(Search.getStringSpecification(productDetailSearch.getCategoryDescriptionValue(), productDetailSearch.getCategoryDescriptionField(), productDetailSearch.getCategoryDescriptionSearchType()))

                .and(Search.getStringSpecification(productDetailSearch.getQuantityPerUnitValue(), productDetailSearch.getQuantityPerUnitField(), productDetailSearch.getQuantityPerUnitSearchType()))

                .and(Search.getIntegerSpecification(productDetailSearch.getUnitPriceValue(), productDetailSearch.getUnitPriceField(), productDetailSearch.getUnitPriceSearchType()))

                .and(Search.getIntegerSpecification(productDetailSearch.getUnitsInStockValue(), productDetailSearch.getUnitsInStockField(), productDetailSearch.getUnitsInStockSearchType()))

                .and(Search.getIntegerSpecification(productDetailSearch.getUnitsOnOrderValue(), productDetailSearch.getUnitsOnOrderField(), productDetailSearch.getUnitsOnOrderSearchType()))

                .and(Search.getIntegerSpecification(productDetailSearch.getReorderLevelValue(), productDetailSearch.getReorderLevelField(), productDetailSearch.getReorderLevelSearchType()))

                .and(Search.getIntegerSpecification(productDetailSearch.getDiscontinuedValue(), productDetailSearch.getDiscontinuedField(), productDetailSearch.getDiscontinuedSearchType()))
                ;

        return Specification.class.cast(specification);
    }
}
