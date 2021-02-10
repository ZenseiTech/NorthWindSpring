package com.zenseitech.northwind.product;

import com.zenseitech.northwind.util.Search;
import com.zenseitech.northwind.util.SearchForm;
import com.zenseitech.northwind.util.SearchType;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ProductSearch {

    private Product product;

    private SearchType idSearchType;
    private SearchType productNameSearchType;
    private SearchType supplierIdSearchType;
    private SearchType categoryIdSearchType;
    private SearchType quantityPerUnitSearchType;
    private SearchType unitPriceSearchType;
    private SearchType unitsInStockSearchType;
    private SearchType unitsOnOrderSearchType;
    private SearchType reorderLevelSearchType;
    private SearchType discontinuedSearchType;

    private String idField;
    private String productNameField;
    private String supplierIdField;
    private String categoryIdField;
    private String quantityPerUnitField;
    private String unitPriceField;
    private String unitsInStockField;
    private String unitsOnOrderField;
    private String reorderLevelField;
    private String discontinuedField;

    public ProductSearch() {
    }

    public static ProductSearch get(SearchForm searchForm) {
        Product product = new Product();
        ProductSearch productSearch = new ProductSearch();
        productSearch.setProduct(product);

        for(Search search : searchForm.getSearch()) {
            if(search.getField().equalsIgnoreCase("id")) {
                product.setId((String)search.getValue());
                productSearch.setIdField(search.getField());
                productSearch.setIdSearchType(SearchType.valueOf(search.getOperator().toUpperCase().toUpperCase()));

            } else if(search.getField().equalsIgnoreCase("productName")) {
                product.setProductName((String)search.getValue());
                productSearch.setProductNameField(search.getField());
                productSearch.setProductNameSearchType(SearchType.valueOf(search.getOperator().toUpperCase()));

            } else if(search.getField().equalsIgnoreCase("supplierId")) {
                product.setSupplierId((Integer)search.getValue());
                productSearch.setSupplierIdField(search.getField());
                productSearch.setSupplierIdSearchType(SearchType.valueOf(search.getOperator().toUpperCase()));

            } else if(search.getField().equalsIgnoreCase("categoryId")) {
                product.setCategoryId((Integer)search.getValue());
                productSearch.setCategoryIdField(search.getField());
                productSearch.setCategoryIdSearchType(SearchType.valueOf(search.getOperator().toUpperCase()));

            } else if(search.getField().equalsIgnoreCase("quantityPerUnit")) {
                product.setQuantityPerUnit((String)search.getValue());
                productSearch.setQuantityPerUnitField(search.getField());
                productSearch.setQuantityPerUnitSearchType(SearchType.valueOf(search.getOperator().toUpperCase()));

            } else if(search.getField().equalsIgnoreCase("unitPrice")) {
                product.setUnitPrice((BigDecimal)search.getValue());
                productSearch.setUnitPriceField(search.getField());
                productSearch.setUnitPriceSearchType(SearchType.valueOf(search.getOperator().toUpperCase()));

            } else if(search.getField().equalsIgnoreCase("unitsInStock")) {
                product.setUnitsInStock((Integer)search.getValue());
                productSearch.setUnitsInStockField(search.getField());
                productSearch.setUnitsInStockSearchType(SearchType.valueOf(search.getOperator().toUpperCase()));

            } else if(search.getField().equalsIgnoreCase("unitsOnOrder")) {
                product.setUnitsOnOrder((Integer)search.getValue());
                productSearch.setUnitsOnOrderField(search.getField());
                productSearch.setUnitsOnOrderSearchType(SearchType.valueOf(search.getOperator().toUpperCase()));

            } else if(search.getField().equalsIgnoreCase("reorderLevel")) {
                product.setReorderLevel((Integer)search.getValue());
                productSearch.setReorderLevelField(search.getField());
                productSearch.setReorderLevelSearchType(SearchType.valueOf(search.getOperator().toUpperCase()));

            } else if(search.getField().equalsIgnoreCase("discontinued")) {
                product.setDiscontinued((Integer)search.getValue());
                productSearch.setDiscontinuedField(search.getField());
                productSearch.setDiscontinuedSearchType(SearchType.valueOf(search.getOperator().toUpperCase()));

            }
        }
        return productSearch;
    }
}
