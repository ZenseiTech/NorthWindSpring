package com.zenseitech.northwind.product;

import com.zenseitech.northwind.util.Search;
import com.zenseitech.northwind.util.SearchForm;
import com.zenseitech.northwind.util.SearchType;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class ProductSearch {

    private SearchType idSearchType;
    private SearchType productNameSearchType;
    private SearchType supplierCompanyNameSearchType;
    private SearchType categoryNameSearchType;
    private SearchType quantityPerUnitSearchType;
    private SearchType unitPriceSearchType;
    private SearchType unitsInStockSearchType;
    private SearchType unitsOnOrderSearchType;
    private SearchType reorderLevelSearchType;
    private SearchType discontinuedSearchType;

    private String idField;
    private String productNameField;
    private String supplierCompanyNameField;
    private String categoryNameField;
    private String quantityPerUnitField;
    private String unitPriceField;
    private String unitsInStockField;
    private String unitsOnOrderField;
    private String reorderLevelField;
    private String discontinuedField;

    private List<Integer> idValue = new ArrayList<>();
    private String productNameValue;
    private String supplierCompanyNameValue;
    private String categoryNameValue;
    private String quantityPerUnitValue;
    private List<Integer> unitPriceValue = new ArrayList<>();
    private List<Integer> unitsInStockValue = new ArrayList<>();
    private List<Integer> unitsOnOrderValue = new ArrayList<>();
    private List<Integer> reorderLevelValue = new ArrayList<>();
    private List<Integer> discontinuedValue = new ArrayList<>();

    public ProductSearch() {
    }

    public static ProductSearch get(SearchForm searchForm) {
        ProductSearch productSearch = new ProductSearch();

        for (Search search : searchForm.getSearch()) {
            if (search.getField().equalsIgnoreCase("id")) {
                if (search.getValue() instanceof List) {
                    productSearch.idValue = (List) search.getValue();
                } else {
                    productSearch.idValue.add((Integer) search.getValue());
                }
                productSearch.setIdField(search.getField());
                productSearch.setIdSearchType(SearchType.valueOf(search.getOperator().toUpperCase().toUpperCase()));

            } else if (search.getField().equalsIgnoreCase("productName")) {
                productSearch.setProductNameValue((String) search.getValue());
                productSearch.setProductNameField(search.getField());
                productSearch.setProductNameSearchType(SearchType.valueOf(search.getOperator().toUpperCase()));

            } else if (search.getField().equalsIgnoreCase("supplierCompanyName")) {
                productSearch.setSupplierCompanyNameValue((String) search.getValue());
                productSearch.setSupplierCompanyNameField("supplier");
                productSearch.setSupplierCompanyNameSearchType(SearchType.valueOf(search.getOperator().toUpperCase()));

            } else if (search.getField().equalsIgnoreCase("categoryName")) {
                productSearch.setCategoryNameValue((String) search.getValue());
                productSearch.setCategoryNameField("category");
                productSearch.setCategoryNameSearchType(SearchType.valueOf(search.getOperator().toUpperCase()));

            } else if (search.getField().equalsIgnoreCase("quantityPerUnit")) {
                productSearch.setQuantityPerUnitValue((String) search.getValue());
                productSearch.setQuantityPerUnitField(search.getField());
                productSearch.setQuantityPerUnitSearchType(SearchType.valueOf(search.getOperator().toUpperCase()));

            } else if (search.getField().equalsIgnoreCase("unitPrice")) {
                if (search.getValue() instanceof List) {
                    productSearch.unitPriceValue = (List) search.getValue();
                } else {
                    productSearch.unitPriceValue.add((Integer) search.getValue());
                }
                productSearch.setUnitPriceField(search.getField());
                productSearch.setUnitPriceSearchType(SearchType.valueOf(search.getOperator().toUpperCase()));

            } else if (search.getField().equalsIgnoreCase("unitsInStock")) {
                if (search.getValue() instanceof List) {
                    productSearch.unitsInStockValue = (List) search.getValue();
                } else {
                    productSearch.unitsInStockValue.add((Integer) search.getValue());
                }
                productSearch.setUnitsInStockField(search.getField());
                productSearch.setUnitsInStockSearchType(SearchType.valueOf(search.getOperator().toUpperCase()));

            } else if (search.getField().equalsIgnoreCase("unitsOnOrder")) {
                if (search.getValue() instanceof List) {
                    productSearch.unitsOnOrderValue = (List) search.getValue();
                } else {
                    productSearch.unitsOnOrderValue.add((Integer) search.getValue());
                }
                productSearch.setUnitsOnOrderField(search.getField());
                productSearch.setUnitsOnOrderSearchType(SearchType.valueOf(search.getOperator().toUpperCase()));

            } else if (search.getField().equalsIgnoreCase("reorderLevel")) {
                if (search.getValue() instanceof List) {
                    productSearch.reorderLevelValue = (List) search.getValue();
                } else {
                    productSearch.reorderLevelValue.add((Integer) search.getValue());
                }
                productSearch.setReorderLevelField(search.getField());
                productSearch.setReorderLevelSearchType(SearchType.valueOf(search.getOperator().toUpperCase()));

            } else if (search.getField().equalsIgnoreCase("discontinued")) {
                if (search.getValue() instanceof List) {
                    productSearch.discontinuedValue = (List) search.getValue();
                } else {
                    productSearch.discontinuedValue.add((Integer) search.getValue());
                }
                productSearch.setDiscontinuedField(search.getField());
                productSearch.setDiscontinuedSearchType(SearchType.valueOf(search.getOperator().toUpperCase()));
            }
        }
        return productSearch;
    }
}
