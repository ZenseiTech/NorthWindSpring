package com.zenseitech.northwind.product;

import com.zenseitech.northwind.category.Category;
import com.zenseitech.northwind.category.CategoryRepository;
import com.zenseitech.northwind.supplier.Supplier;
import com.zenseitech.northwind.supplier.SupplierRepository;
import com.zenseitech.northwind.util.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceDefault implements ProductService {

    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductServiceDefault(ProductRepository productRepository,
                                 SupplierRepository supplierRepository,
                                 CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Page<Product> search(Pageable pageable) {
        return this.productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> search(ProductSearch productSearch, Pageable pageable) {
        Specification<Product> specification = getSpecification(productSearch);
        return this.productRepository.findAll(specification, pageable);
    }

    protected Specification<Product> getSpecification(ProductSearch productSearch) {
        Specification<Object> specification = Specification
                .where(Search.getIntegerSpecification(productSearch.getIdValue(), productSearch.getIdField(), productSearch.getIdSearchType()))

                .and(Search.getStringSpecification(productSearch.getProductNameValue(), productSearch.getProductNameField(), productSearch.getProductNameSearchType()))

                .and(Search.getIntegerSpecification(getSupplierId(productSearch.getSupplierNameValue()), productSearch.getSupplierNameField(), productSearch.getSupplierNameSearchType()))

                .and(Search.getIntegerSpecification(getCategoryId(productSearch.getCategoryNameValue()), productSearch.getCategoryNameField(), productSearch.getCategoryNameSearchType()))

                .and(Search.getStringSpecification(productSearch.getQuantityPerUnitValue(), productSearch.getQuantityPerUnitField(), productSearch.getQuantityPerUnitSearchType()))

                .and(Search.getIntegerSpecification(productSearch.getUnitPriceValue(), productSearch.getUnitPriceField(), productSearch.getUnitPriceSearchType()))

                .and(Search.getIntegerSpecification(productSearch.getUnitsInStockValue(), productSearch.getUnitsInStockField(), productSearch.getUnitsInStockSearchType()))

                .and(Search.getIntegerSpecification(productSearch.getUnitsOnOrderValue(), productSearch.getUnitsOnOrderField(), productSearch.getUnitsOnOrderSearchType()))

                .and(Search.getIntegerSpecification(productSearch.getReorderLevelValue(), productSearch.getReorderLevelField(), productSearch.getReorderLevelSearchType()))

                .and(Search.getIntegerSpecification(productSearch.getDiscontinuedValue(), productSearch.getDiscontinuedField(), productSearch.getDiscontinuedSearchType()))
                ;

        return Specification.class.cast(specification);
    }

    private List<Integer> getSupplierId(String supplierCompanyName) {
        List<Integer> supplierIds = new ArrayList<>();
        if(supplierCompanyName != null) {
            Supplier supplier = findSupplierByCompanyName(supplierCompanyName);
            supplierIds.add(supplier.getId());
        }
        return supplierIds;
    }

    private Supplier findSupplierByCompanyName(String supplierCompanyName) {
        return supplierRepository.findByCompanyName(supplierCompanyName);
    }

    private List<Integer> getCategoryId(String categoryName) {
        List<Integer> categoryIds = new ArrayList<>();
        if(categoryName != null) {
            Category category = findCategoryName(categoryName);
            categoryIds.add(category.getId());
        }
        return categoryIds;
    }

    private Category findCategoryName(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName);
    }
}
