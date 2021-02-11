package com.zenseitech.northwind.customer;

import com.zenseitech.northwind.util.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceDefault implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceDefault(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Page<Customer> search(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public Page<Customer> search(CustomerSearch customerSearch, Pageable pageable) {
        return customerRepository.findAll(getSpecification(customerSearch), pageable);
    }

    protected Specification<Customer> getSpecification(CustomerSearch customerSearch) {

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
