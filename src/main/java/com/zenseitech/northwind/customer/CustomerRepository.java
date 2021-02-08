package com.zenseitech.northwind.customer;

import com.zenseitech.northwind.SearchType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.text.MessageFormat;

public interface CustomerRepository extends JpaRepository<Customer, String>, JpaSpecificationExecutor<Customer> {

    Page<Customer> findAll(Pageable pageable);

    static Specification<Customer> getSpecification(String expression, String field, SearchType searchType) {
        if(searchType == null) {
            searchType = SearchType.IS;
        }
        switch(searchType) {
            case CONTAINS:
                return (root, query, builder) -> builder.like(root.get(field), contains(expression));
            case BEGINS:
                return (root, query, builder) -> builder.like(root.get(field), startWith(expression));
            case ENDS:
                return (root, query, builder) -> builder.like(root.get(field), endWith(expression));
            default:
                return (root, query, builder) -> builder.like(root.get(field), expression);
        }
    }

    static Specification<Customer> getSpecification(CustomerSearch customerSearch) {
        Specification<Customer> specification = Specification
                .where(customerSearch.getCustomer().getId() == null ? null : getSpecification(customerSearch.getCustomer().getId(), "id", customerSearch.getIdSearchType()))
                .and(customerSearch.getCustomer().getCompanyName() == null ? null : getSpecification(customerSearch.getCustomer().getCompanyName(), "companyName", customerSearch.getCompanyNameSearchType()))
                .and(customerSearch.getCustomer().getContactName() == null ? null : getSpecification(customerSearch.getCustomer().getContactName(), "contactName", customerSearch.getContactNameSearchType()))
                .and(customerSearch.getCustomer().getContactTitle() == null ? null : getSpecification(customerSearch.getCustomer().getContactTitle(), "contactTitle", customerSearch.getContactTitleSearchType()))
                .and(customerSearch.getCustomer().getAddress() == null ? null : getSpecification(customerSearch.getCustomer().getAddress(), "address", customerSearch.getAddressSearchType()))
                .and(customerSearch.getCustomer().getCity() == null ? null : getSpecification(customerSearch.getCustomer().getCity(), "city", customerSearch.getCitySearchType()))
                .and(customerSearch.getCustomer().getRegion() == null ? null : getSpecification(customerSearch.getCustomer().getRegion(), "region", customerSearch.getRegionSearchType()))
                .and(customerSearch.getCustomer().getCountry() == null ? null : getSpecification(customerSearch.getCustomer().getCountry(), "country", customerSearch.getCountrySearchType()))
                .and(customerSearch.getCustomer().getPhone() == null ? null : getSpecification(customerSearch.getCustomer().getPhone(), "phone", customerSearch.getPhoneSearchType()))
                .and(customerSearch.getCustomer().getFax() == null ? null : getSpecification(customerSearch.getCustomer().getFax(), "fax", customerSearch.getFaxSearchType()))
                .and(customerSearch.getCustomer().getPostalCode() == null ? null : getSpecification(customerSearch.getCustomer().getPostalCode(), "postalCode", customerSearch.getPostalCodeSearchType()));
        return specification;
    }

    private static String contains(String expression) {
        return MessageFormat.format("%{0}%", expression);
    }

    private static String startWith(String expression) {
        return MessageFormat.format("%{0}", expression);
    }

    private static String endWith(String expression) {
        return MessageFormat.format("{0}%", expression);
    }

}
