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
        Customer customer = customerSearch.getCustomer();
        Specification<Customer> specification = Specification
                .where(customer.getId() == null ? null :
                        Search.getStringSpecification(customer.getId(), customerSearch.getIdField(), customerSearch.getIdSearchType()))
                .and(customer.getCompanyName() == null ? null :
                        Search.getStringSpecification(customer.getCompanyName(), customerSearch.getCompanyNameField(), customerSearch.getCompanyNameSearchType()))
                .and(customer.getContactName() == null ? null :
                        Search.getStringSpecification(customer.getContactName(), customerSearch.getContactNameField(), customerSearch.getContactNameSearchType()))
                .and(customer.getContactTitle() == null ? null :
                        Search.getStringSpecification(customer.getContactTitle(), customerSearch.getContactTitleField(), customerSearch.getContactTitleSearchType()))
                .and(customer.getAddress() == null ? null :
                        Search.getStringSpecification(customer.getAddress(), customerSearch.getAddressField(), customerSearch.getAddressSearchType()))
                .and(customer.getCity() == null ? null :
                        Search.getStringSpecification(customer.getCity(), customerSearch.getCityField(), customerSearch.getCitySearchType()))
                .and(customer.getRegion() == null ? null :
                        Search.getStringSpecification(customer.getRegion(), customerSearch.getRegionField(), customerSearch.getRegionSearchType()))
                .and(customer.getCountry() == null ? null :
                        Search.getStringSpecification(customer.getCountry(), customerSearch.getCountryField(), customerSearch.getCountrySearchType()))
                .and(customer.getPhone() == null ? null :
                        Search.getStringSpecification(customer.getPhone(), customerSearch.getPhoneField(), customerSearch.getPhoneSearchType()))
                .and(customer.getFax() == null ? null :
                        Search.getStringSpecification(customer.getFax(), customerSearch.getFaxField(), customerSearch.getFaxSearchType()))
                .and(customer.getPostalCode() == null ? null :
                        Search.getStringSpecification(customer.getPostalCode(), customerSearch.getPostalCodeField(), customerSearch.getPostalCodeSearchType()));
        return specification;
    }
}
