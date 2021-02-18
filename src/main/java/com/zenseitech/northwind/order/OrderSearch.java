package com.zenseitech.northwind.order;

import com.zenseitech.northwind.util.Search;
import com.zenseitech.northwind.util.SearchForm;
import com.zenseitech.northwind.util.SearchType;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class OrderSearch {

    private SearchType idSearchType;
    private String idField;
    private List<Integer> idValue = new ArrayList<>();

    private SearchType employeeFullNameSearchType;
    private String employeeFullNameField;
    private String employeeFullNameValue;

    private SearchType orderDateSearchType;
    private String orderDateField;
    private List<String> orderDateValue = new ArrayList<>();

    private SearchType requiredDateSearchType;
    private String requiredDateField;
    private String requiredDateValue;

    private SearchType shippedDateSearchType;
    private String shippedDateField;
    private String shippedDateValue;

    private SearchType freightSearchType;
    private String freightField;
    private List<BigDecimal> freightValue = new ArrayList<>();

    private SearchType shipNameSearchType;
    private String shipNameField;
    private String shipNameValue;

    private SearchType shipCompanyNameSearchType;
    private String shipCompanyNameField;
    private String shipCompanyNameValue;

    private SearchType customerCompanyNameSearchType;
    private String customerCompanyNameField;
    private String customerCompanyNameValue;

    private SearchType shipAddressSearchType;
    private String shipAddressField;
    private String shipAddressValue;

    private SearchType shipCitySearchType;
    private String shipCityField;
    private String shipCityValue;

    private SearchType shipRegionSearchType;
    private String shipRegionField;
    private String shipRegionValue;

    private SearchType shipPostalCodeSearchType;
    private String shipPostalCodeField;
    private String shipPostalCodeValue;

    private SearchType shipCountrySearchType;
    private String shipCountryField;
    private String shipCountryValue;

    public OrderSearch() {
    }

    public static OrderSearch get(SearchForm searchForm) {
        OrderSearch orderSearch = new OrderSearch();

        for (Search search : searchForm.getSearch()) {
            if (search.getField().equalsIgnoreCase("id")) {
                if (search.getValue() instanceof List) {
                    orderSearch.idValue = (List) search.getValue();
                } else {
                    orderSearch.idValue.add((Integer) search.getValue());
                }
                orderSearch.setIdField(search.getField());
                orderSearch.setIdSearchType(SearchType.valueOf(search.getOperator().toUpperCase().toUpperCase()));

            } else if (search.getField().equalsIgnoreCase("employeeFullName")) {
                orderSearch.setEmployeeFullNameValue((String) search.getValue());
                orderSearch.setEmployeeFullNameField("employee");
                orderSearch.setEmployeeFullNameSearchType(SearchType.valueOf(search.getOperator().toUpperCase()));

            } else if (search.getField().equalsIgnoreCase("orderDate")) {
                if(search.getValue() instanceof List) {
                    orderSearch.orderDateValue = (List) search.getValue();
                } else {
                    orderSearch.orderDateValue.add((String) search.getValue());
                }
                orderSearch.setOrderDateField(search.getField());
                orderSearch.setOrderDateSearchType(SearchType.valueOf(search.getOperator().toUpperCase()));

            } else if (search.getField().equalsIgnoreCase("requiredDate")) {
                orderSearch.setRequiredDateValue((String) search.getValue());
                orderSearch.setRequiredDateField(search.getField());
                orderSearch.setRequiredDateSearchType(SearchType.valueOf(search.getOperator().toUpperCase()));

            } else if (search.getField().equalsIgnoreCase("shippedDate")) {
                orderSearch.setShippedDateValue((String) search.getValue());
                orderSearch.setShippedDateField(search.getField());
                orderSearch.setShippedDateSearchType(SearchType.valueOf(search.getOperator().toUpperCase()));

            } else if (search.getField().equalsIgnoreCase("freight")) {
                if(search.getValue() instanceof List) {
                    orderSearch.freightValue = (List) search.getValue();
                } else {
                    orderSearch.freightValue.add((BigDecimal) search.getValue());
                }
                orderSearch.setFreightField(search.getField());
                orderSearch.setFreightSearchType(SearchType.valueOf(search.getOperator().toUpperCase()));

            } else if (search.getField().equalsIgnoreCase("shipName")) {
                orderSearch.setShipNameValue((String) search.getValue());
                orderSearch.setShipNameField(search.getField());
                orderSearch.setShipNameSearchType(SearchType.valueOf(search.getOperator().toUpperCase()));

            } else if (search.getField().equalsIgnoreCase("shipCompanyName")) {
                orderSearch.setShipCompanyNameValue((String) search.getValue());
                orderSearch.setShipCompanyNameField("shipper");
                orderSearch.setShipCompanyNameSearchType(SearchType.valueOf(search.getOperator().toUpperCase()));

            } else if (search.getField().equalsIgnoreCase("customerCompanyName")) {
                orderSearch.setCustomerCompanyNameValue((String) search.getValue());
                orderSearch.setCustomerCompanyNameField("customerId");
                orderSearch.setCustomerCompanyNameSearchType(SearchType.valueOf(search.getOperator().toUpperCase()));

            } else if (search.getField().equalsIgnoreCase("shipAddress")) {
                orderSearch.setShipAddressValue((String) search.getValue());
                orderSearch.setShipAddressField(search.getField());
                orderSearch.setShipAddressSearchType(SearchType.valueOf(search.getOperator().toUpperCase()));

            } else if (search.getField().equalsIgnoreCase("shipCity")) {
                orderSearch.setShipCityValue((String) search.getValue());
                orderSearch.setShipCityField(search.getField());
                orderSearch.setShipCitySearchType(SearchType.valueOf(search.getOperator().toUpperCase()));

            } else if (search.getField().equalsIgnoreCase("shipRegion")) {
                orderSearch.setShipRegionValue((String) search.getValue());
                orderSearch.setShipRegionField(search.getField());
                orderSearch.setShipRegionSearchType(SearchType.valueOf(search.getOperator().toUpperCase()));

            } else if (search.getField().equalsIgnoreCase("shipPostalCode")) {
                orderSearch.setShipPostalCodeValue((String) search.getValue());
                orderSearch.setShipPostalCodeField(search.getField());
                orderSearch.setShipPostalCodeSearchType(SearchType.valueOf(search.getOperator().toUpperCase()));

            } else if (search.getField().equalsIgnoreCase("shipCountry")) {
                orderSearch.setShipCountryValue((String) search.getValue());
                orderSearch.setShipCountryField(search.getField());
                orderSearch.setShipCountrySearchType(SearchType.valueOf(search.getOperator().toUpperCase()));

            }
        }
        return orderSearch;
    }
}
