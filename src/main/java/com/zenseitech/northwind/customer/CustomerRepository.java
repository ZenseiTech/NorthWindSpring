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
                .where(customerSearch.getIdValue() == null ? null :
                        Search.getStringSpecification(customerSearch.getIdValue(), customerSearch.getIdField(), customerSearch.getIdSearchType()))
                .and(customerSearch.getCompanyNameValue() == null ? null :
                        Search.getStringSpecification(customerSearch.getCompanyNameValue(), customerSearch.getCompanyNameField(), customerSearch.getCompanyNameSearchType()))
                .and(customerSearch.getContactNameValue() == null ? null :
                        Search.getStringSpecification(customerSearch.getContactNameValue(), customerSearch.getContactNameField(), customerSearch.getContactNameSearchType()))
                .and(customerSearch.getContactTitleValue() == null ? null :
                        Search.getStringSpecification(customerSearch.getContactTitleValue(), customerSearch.getContactTitleField(), customerSearch.getContactTitleSearchType()))
                .and(customerSearch.getAddressValue() == null ? null :
                        Search.getStringSpecification(customerSearch.getAddressValue(), customerSearch.getAddressField(), customerSearch.getAddressSearchType()))
                .and(customerSearch.getCityValue() == null ? null :
                        Search.getStringSpecification(customerSearch.getCityValue(), customerSearch.getCityField(), customerSearch.getCitySearchType()))
                .and(customerSearch.getRegionValue() == null ? null :
                        Search.getStringSpecification(customerSearch.getRegionValue(), customerSearch.getRegionField(), customerSearch.getRegionSearchType()))
                .and(customerSearch.getCountryValue() == null ? null :
                        Search.getStringSpecification(customerSearch.getCountryValue(), customerSearch.getCountryField(), customerSearch.getCountrySearchType()))
                .and(customerSearch.getPhoneValue() == null ? null :
                        Search.getStringSpecification(customerSearch.getPhoneValue(), customerSearch.getPhoneField(), customerSearch.getPhoneSearchType()))
                .and(customerSearch.getFaxValue() == null ? null :
                        Search.getStringSpecification(customerSearch.getFaxValue(), customerSearch.getFaxField(), customerSearch.getFaxSearchType()))
                .and(customerSearch.getPostalCodeValue() == null ? null :
                        Search.getStringSpecification(customerSearch.getPostalCodeValue(), customerSearch.getPostalCodeField(), customerSearch.getPostalCodeSearchType()));
        return Specification.class.cast(specification);
    }
}
