package com.zenseitech.northwind.productDetail;

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
public class ProductDetailSearch {

    private SearchType idSearchType;
    private SearchType productNameSearchType;
    private SearchType supplierNameSearchType;
    private SearchType supplierRegionSearchType;
    private SearchType categoryNameSearchType;
    private SearchType categoryDescriptionSearchType;
    private SearchType quantityPerUnitSearchType;
    private SearchType unitPriceSearchType;
    private SearchType unitsInStockSearchType;
    private SearchType unitsOnOrderSearchType;
    private SearchType reorderLevelSearchType;
    private SearchType discontinuedSearchType;

    private String idField;
    private String productNameField;
    private String supplierNameField;
    private String supplierRegionField;
    private String categoryNameField;
    private String categoryDescriptionField;
    private String quantityPerUnitField;
    private String unitPriceField;
    private String unitsInStockField;
    private String unitsOnOrderField;
    private String reorderLevelField;
    private String discontinuedField;

    private List<Integer> idValue = new ArrayList<>();
    private String productNameValue;
    private String supplierNameValue;
    private String supplierRegionValue;
    private String categoryNameValue;
    private String categoryDescriptionValue;
    private String quantityPerUnitValue;
    private List<Integer> unitPriceValue = new ArrayList<>();
    private List<Integer> unitsInStockValue = new ArrayList<>();
    private List<Integer> unitsOnOrderValue = new ArrayList<>();
    private List<Integer> reorderLevelValue = new ArrayList<>();
    private List<Integer> discontinuedValue = new ArrayList<>();

    public ProductDetailSearch() {
    }

    public static ProductDetailSearch get(SearchForm searchForm) {
        ProductDetailSearch productDetailSearch = new ProductDetailSearch();

        for (Search search : searchForm.getSearch()) {
            if (search.getField().equalsIgnoreCase("id")) {
                if (search.getValue() instanceof List) {
                    productDetailSearch.idValue = (List) search.getValue();
                } else {
                    productDetailSearch.idValue.add((Integer) search.getValue());
                }
                productDetailSearch.setIdField(search.getField());
                productDetailSearch.setIdSearchType(SearchType.valueOf(search.getOperator().toUpperCase().toUpperCase()));

            } else if (search.getField().equalsIgnoreCase("productName")) {
                productDetailSearch.setProductNameValue((String) search.getValue());
                productDetailSearch.setProductNameField(search.getField());
                productDetailSearch.setProductNameSearchType(SearchType.valueOf(search.getOperator().toUpperCase()));

            } else if (search.getField().equalsIgnoreCase("supplierName")) {
                productDetailSearch.setSupplierNameValue((String) search.getValue());
                productDetailSearch.setSupplierNameField(search.getField());
                productDetailSearch.setSupplierNameSearchType(SearchType.valueOf(search.getOperator().toUpperCase()));

            } else if (search.getField().equalsIgnoreCase("supplierRegion")) {
                productDetailSearch.setSupplierRegionValue((String) search.getValue());
                productDetailSearch.setSupplierRegionField(search.getField());
                productDetailSearch.setSupplierRegionSearchType(SearchType.valueOf(search.getOperator().toUpperCase()));

            } else if (search.getField().equalsIgnoreCase("categoryName")) {
                productDetailSearch.setCategoryNameValue((String) search.getValue());
                productDetailSearch.setCategoryNameField(search.getField());
                productDetailSearch.setCategoryNameSearchType(SearchType.valueOf(search.getOperator().toUpperCase()));

            } else if (search.getField().equalsIgnoreCase("categoryDescription")) {
                    productDetailSearch.setCategoryDescriptionValue((String) search.getValue());
                    productDetailSearch.setCategoryDescriptionField(search.getField());
                    productDetailSearch.setCategoryDescriptionSearchType(SearchType.valueOf(search.getOperator().toUpperCase()));

            } else if (search.getField().equalsIgnoreCase("quantityPerUnit")) {
                productDetailSearch.setQuantityPerUnitValue((String) search.getValue());
                productDetailSearch.setQuantityPerUnitField(search.getField());
                productDetailSearch.setQuantityPerUnitSearchType(SearchType.valueOf(search.getOperator().toUpperCase()));

            } else if (search.getField().equalsIgnoreCase("unitPrice")) {
                if (search.getValue() instanceof List) {
                    productDetailSearch.unitPriceValue = (List) search.getValue();
                } else {
                    productDetailSearch.unitPriceValue.add((Integer) search.getValue());
                }
                productDetailSearch.setUnitPriceField(search.getField());
                productDetailSearch.setUnitPriceSearchType(SearchType.valueOf(search.getOperator().toUpperCase()));

            } else if (search.getField().equalsIgnoreCase("unitsInStock")) {
                if (search.getValue() instanceof List) {
                    productDetailSearch.unitsInStockValue = (List) search.getValue();
                } else {
                    productDetailSearch.unitsInStockValue.add((Integer) search.getValue());
                }
                productDetailSearch.setUnitsInStockField(search.getField());
                productDetailSearch.setUnitsInStockSearchType(SearchType.valueOf(search.getOperator().toUpperCase()));

            } else if (search.getField().equalsIgnoreCase("unitsOnOrder")) {
                if (search.getValue() instanceof List) {
                    productDetailSearch.unitsOnOrderValue = (List) search.getValue();
                } else {
                    productDetailSearch.unitsOnOrderValue.add((Integer) search.getValue());
                }
                productDetailSearch.setUnitsOnOrderField(search.getField());
                productDetailSearch.setUnitsOnOrderSearchType(SearchType.valueOf(search.getOperator().toUpperCase()));

            } else if (search.getField().equalsIgnoreCase("reorderLevel")) {
                if (search.getValue() instanceof List) {
                    productDetailSearch.reorderLevelValue = (List) search.getValue();
                } else {
                    productDetailSearch.reorderLevelValue.add((Integer) search.getValue());
                }
                productDetailSearch.setReorderLevelField(search.getField());
                productDetailSearch.setReorderLevelSearchType(SearchType.valueOf(search.getOperator().toUpperCase()));

            } else if (search.getField().equalsIgnoreCase("discontinued")) {
                if(((String) search.getValue()).equalsIgnoreCase("Y")) {
                    productDetailSearch.discontinuedValue.add(1);
                } else {
                    productDetailSearch.discontinuedValue.add(0);
                }
                productDetailSearch.setDiscontinuedField(search.getField());
                productDetailSearch.setDiscontinuedSearchType(SearchType.valueOf(search.getOperator().toUpperCase()));
            }
        }
        return productDetailSearch;
    }
}
