package com.zenseitech.northwind.customer;

import com.zenseitech.northwind.util.Search;
import com.zenseitech.northwind.util.SearchForm;
import com.zenseitech.northwind.util.SearchType;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CustomerSearch {

//    private Customer customer;

    private SearchType idSearchType;
    private SearchType companyNameSearchType;
    private SearchType contactNameSearchType;
    private SearchType contactTitleSearchType;
    private SearchType addressSearchType;
    private SearchType citySearchType;
    private SearchType regionSearchType;
    private SearchType postalCodeSearchType;
    private SearchType countrySearchType;
    private SearchType phoneSearchType;
    private SearchType faxSearchType;

    private String idField;
    private String companyNameField;
    private String contactNameField;
    private String contactTitleField;
    private String addressField;
    private String cityField;
    private String regionField;
    private String postalCodeField;
    private String countryField;
    private String phoneField;
    private String faxField;

    private String idValue;
    private String companyNameValue;
    private String contactNameValue;
    private String contactTitleValue;
    private String addressValue;
    private String cityValue;
    private String regionValue;
    private String postalCodeValue;
    private String countryValue;
    private String phoneValue;
    private String faxValue;

    public CustomerSearch() {

    }

    public static CustomerSearch get(SearchForm searchForm) {
//        Customer customer = new Customer();
        CustomerSearch customerSearch = new CustomerSearch();
//        customerSearch.setCustomer(customer);

        for(Search search : searchForm.getSearch()) {
            if(search.getField().equalsIgnoreCase("id")) {
                customerSearch.setIdValue((String)search.getValue());
                customerSearch.setIdField(search.getField());
                customerSearch.setIdSearchType(SearchType.valueOf(search.getOperator().toUpperCase().toUpperCase()));

            } else if(search.getField().equalsIgnoreCase("companyName")) {
                customerSearch.setCompanyNameValue((String)search.getValue());
                customerSearch.setCompanyNameField(search.getField());
                customerSearch.setCompanyNameSearchType(SearchType.valueOf(search.getOperator().toUpperCase()));

            } else if(search.getField().equalsIgnoreCase("contactName")) {
                customerSearch.setContactNameValue((String)search.getValue());
                customerSearch.setContactNameField(search.getField());
                customerSearch.setContactNameSearchType(SearchType.valueOf(search.getOperator().toUpperCase()));

            } else if(search.getField().equalsIgnoreCase("contactTitle")) {
                customerSearch.setContactTitleValue((String)search.getValue());
                customerSearch.setContactTitleField(search.getField());
                customerSearch.setContactTitleSearchType(SearchType.valueOf(search.getOperator().toUpperCase()));

            } else if(search.getField().equalsIgnoreCase("address")) {
                customerSearch.setAddressValue((String)search.getValue());
                customerSearch.setAddressField(search.getField());
                customerSearch.setAddressSearchType(SearchType.valueOf(search.getOperator().toUpperCase()));

            } else if(search.getField().equalsIgnoreCase("city")) {
                customerSearch.setCityValue((String)search.getValue());
                customerSearch.setCityField(search.getField());
                customerSearch.setCitySearchType(SearchType.valueOf(search.getOperator().toUpperCase()));

            } else if(search.getField().equalsIgnoreCase("region")) {
                customerSearch.setRegionValue((String)search.getValue());
                customerSearch.setRegionField(search.getField());
                customerSearch.setRegionSearchType(SearchType.valueOf(search.getOperator().toUpperCase()));

            } else if(search.getField().equalsIgnoreCase("postalCode")) {
                customerSearch.setPostalCodeValue((String)search.getValue());
                customerSearch.setPostalCodeField(search.getField());
                customerSearch.setPostalCodeSearchType(SearchType.valueOf(search.getOperator().toUpperCase()));

            } else if(search.getField().equalsIgnoreCase("country")) {
                customerSearch.setCountryValue((String)search.getValue());
                customerSearch.setCountryField(search.getField());
                customerSearch.setCountrySearchType(SearchType.valueOf(search.getOperator().toUpperCase()));

            } else if(search.getField().equalsIgnoreCase("phone")) {
                customerSearch.setPhoneValue((String)search.getValue());
                customerSearch.setPhoneField(search.getField());
                customerSearch.setPhoneSearchType(SearchType.valueOf(search.getOperator().toUpperCase()));

            } else if(search.getField().equalsIgnoreCase("fax")) {
                customerSearch.setFaxValue((String)search.getValue());
                customerSearch.setFaxField(search.getField());
                customerSearch.setFaxSearchType(SearchType.valueOf(search.getOperator().toUpperCase()));

            }
        }
        return customerSearch;
    }
}
