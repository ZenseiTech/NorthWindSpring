package com.zenseitech.northwind.customer;

import com.zenseitech.northwind.util.Search;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CustomerRepository extends JpaRepository<Customer, String>, JpaSpecificationExecutor<Customer> {

    Page<Customer> findAll(Pageable pageable);

    static Specification<Customer> getSpecification(CustomerSearch customerSearch) {

        Specification<Object> specification = Specification
                .where(Search.getStringSpecification(customerSearch.getIdValue(), customerSearch.getIdField(), customerSearch.getIdSearchType()))

                .and(Search.getStringSpecification(customerSearch.getCompanyNameValue(), customerSearch.getCompanyNameField(), customerSearch.getCompanyNameSearchType()))

                .and(Search.getStringSpecification(customerSearch.getContactNameValue(), customerSearch.getContactNameField(), customerSearch.getContactNameSearchType()))

                .and(Search.getStringSpecification(customerSearch.getContactTitleValue(), customerSearch.getContactTitleField(), customerSearch.getContactTitleSearchType()))

                .and(Search.getStringSpecification(customerSearch.getAddressValue(), customerSearch.getAddressField(), customerSearch.getAddressSearchType()))

                .and(Search.getStringSpecification(customerSearch.getCityValue(), customerSearch.getCityField(), customerSearch.getCitySearchType()))

                .and(Search.getStringSpecification(customerSearch.getRegionValue(), customerSearch.getRegionField(), customerSearch.getRegionSearchType()))

                .and(Search.getStringSpecification(customerSearch.getCountryValue(), customerSearch.getCountryField(), customerSearch.getCountrySearchType()))

                .and(Search.getStringSpecification(customerSearch.getPhoneValue(), customerSearch.getPhoneField(), customerSearch.getPhoneSearchType()))

                .and(Search.getStringSpecification(customerSearch.getFaxValue(), customerSearch.getFaxField(), customerSearch.getFaxSearchType()))

                .and(Search.getStringSpecification(customerSearch.getPostalCodeValue(), customerSearch.getPostalCodeField(), customerSearch.getPostalCodeSearchType()));

        return Specification.class.cast(specification);
    }
}
