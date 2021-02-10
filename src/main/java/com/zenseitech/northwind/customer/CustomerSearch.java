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

    private Customer customer;

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

    public CustomerSearch() {

    }

    public static CustomerSearch get(SearchForm searchForm) {
        Customer customer = new Customer();
        CustomerSearch customerSearch = new CustomerSearch();
        customerSearch.setCustomer(customer);

        for(Search search : searchForm.getSearch()) {
            if(search.getField().equalsIgnoreCase("id")) {
                customer.setId((String)search.getValue());
                customerSearch.setIdField(search.getField().toLowerCase());
                customerSearch.setIdSearchType(SearchType.valueOf(search.getOperator().toUpperCase().toUpperCase()));

            } else if(search.getField().equalsIgnoreCase("companyName")) {
                customer.setCompanyName((String)search.getValue());
                customerSearch.setCompanyNameField(search.getField().toLowerCase());
                customerSearch.setCompanyNameSearchType(SearchType.valueOf(search.getOperator().toUpperCase()));

            } else if(search.getField().equalsIgnoreCase("contactName")) {
                customer.setContactName((String)search.getValue());
                customerSearch.setContactNameField(search.getField().toLowerCase());
                customerSearch.setContactNameSearchType(SearchType.valueOf(search.getOperator().toUpperCase()));

            } else if(search.getField().equalsIgnoreCase("contactTitle")) {
                customer.setContactTitle((String)search.getValue());
                customerSearch.setContactTitleField(search.getField().toLowerCase());
                customerSearch.setContactTitleSearchType(SearchType.valueOf(search.getOperator().toUpperCase()));

            } else if(search.getField().equalsIgnoreCase("address")) {
                customer.setAddress((String)search.getValue());
                customerSearch.setAddressField(search.getField().toLowerCase());
                customerSearch.setAddressSearchType(SearchType.valueOf(search.getOperator().toUpperCase()));

            } else if(search.getField().equalsIgnoreCase("city")) {
                customer.setCity((String)search.getValue());
                customerSearch.setCityField(search.getField().toLowerCase());
                customerSearch.setCitySearchType(SearchType.valueOf(search.getOperator().toUpperCase()));

            } else if(search.getField().equalsIgnoreCase("region")) {
                customer.setRegion((String)search.getValue());
                customerSearch.setRegionField(search.getField().toLowerCase());
                customerSearch.setRegionSearchType(SearchType.valueOf(search.getOperator().toUpperCase()));

            } else if(search.getField().equalsIgnoreCase("postalCode")) {
                customer.setPostalCode((String)search.getValue());
                customerSearch.setPostalCodeField(search.getField().toLowerCase());
                customerSearch.setPostalCodeSearchType(SearchType.valueOf(search.getOperator().toUpperCase()));

            } else if(search.getField().equalsIgnoreCase("country")) {
                customer.setCountry((String)search.getValue());
                customerSearch.setCountryField(search.getField().toLowerCase());
                customerSearch.setCountrySearchType(SearchType.valueOf(search.getOperator().toUpperCase()));

            } else if(search.getField().equalsIgnoreCase("phone")) {
                customer.setPhone((String)search.getValue());
                customerSearch.setPhoneField(search.getField().toLowerCase());
                customerSearch.setPhoneSearchType(SearchType.valueOf(search.getOperator().toUpperCase()));

            } else if(search.getField().equalsIgnoreCase("fax")) {
                customer.setFax((String)search.getValue());
                customerSearch.setFaxField(search.getField().toLowerCase());
                customerSearch.setFaxSearchType(SearchType.valueOf(search.getOperator().toUpperCase()));

            }
        }
        return customerSearch;
    }
}
