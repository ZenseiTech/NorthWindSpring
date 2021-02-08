package com.zenseitech.northwind.customer;

import com.zenseitech.northwind.SearchType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
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
}
