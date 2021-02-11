package com.zenseitech.northwind.customer;

import com.zenseitech.northwind.util.SearchType;
import com.zenseitech.northwind.configuration.DataSourceConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
@ContextConfiguration(classes = DataSourceConfiguration.class)
@ActiveProfiles(DataSourceConfiguration.PROFILE_SQLITE)
public class CustomerRepositorySQLiteTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void getInitialCountOfCustomers() {
        assertThat(customerRepository.count()).isEqualTo(91);

        Customer notFoundCustomer = Customer.builder().build();
        notFoundCustomer.setCompanyName("Not Found");
        Customer customer = customerRepository.findById("AROUT").orElse(notFoundCustomer);

        assertThat("AROUT").isEqualTo(customer.getId());

        Customer customer1 = customerRepository.findById("AROU").orElse(notFoundCustomer);
        assertThat(customer1.getCompanyName()).isEqualTo(notFoundCustomer.getCompanyName());
    }

    @Test
    public void savesCustomerCorrectly() {

        Iterable<Customer> customers = customerRepository.findAll();
        assertThat(customers).hasSize(91);

        // having ...
        Customer newCustomer = createCustomer("3", "customer3", "Mexico");

        // when ...
        Customer savedCustomer = customerRepository.save(newCustomer);

        // then ...
        assertThat(savedCustomer.toString()).isEqualTo(newCustomer.toString());
        assertThat(customerRepository.count()).isEqualTo(92);
    }

    @Test
    public void findByMatcher() {
//        https://dimitr.im/writing-dynamic-queries-with-spring-data-jpa
//        https://www.logicbig.com/tutorials/spring-framework/spring-data/query-example-matchers.html

        int offset = 0;
        int size = 3;
        String region = "North";
        String country = "USA";
        Pageable pageable = PageRequest.of(offset, size, Sort.by("CompanyName").ascending());

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withMatcher("region", contains().ignoreCase())
                .withMatcher("country", contains().ignoreCase());
        Customer customer = Customer
                .builder()
                .region(region)
                .country(country)
                .build();
        Page<Customer> customerPage = customerRepository.findAll(Example.of(customer, matcher), pageable);

        assertThat(customerPage.getNumberOfElements()).isEqualTo(3);
        assertThat(customerPage.getTotalElements()).isEqualTo(13);
    }

    @Test
    public void findBySpecification_Country() {
//        https://dimitr.im/writing-dynamic-queries-with-spring-data-jpa
//        https://www.logicbig.com/tutorials/spring-framework/spring-data/query-example-matchers.html

        int offset = 0;
        int size = 3;
        CustomerSearch customerSearch = CustomerSearch.builder()
                .countryValue("USA")
                .countryField("country")
                .countrySearchType(SearchType.IS)
                .build();

        Pageable pageable = PageRequest.of(offset, size, Sort.by("CompanyName").ascending());
        Page<Customer> customerPage = customerRepository.findAll(CustomerRepository.getSpecification(customerSearch), pageable);

        assertThat(customerPage.getNumberOfElements()).isEqualTo(3);
        assertThat(customerPage.getTotalElements()).isEqualTo(13);
    }


    @Test
    public void findBySpecification_Region() {
//        https://dimitr.im/writing-dynamic-queries-with-spring-data-jpa
//        https://www.logicbig.com/tutorials/spring-framework/spring-data/query-example-matchers.html

        int offset = 0;
        int size = 3;

        CustomerSearch customerSearch = CustomerSearch.builder()
                .regionValue("Western")
                .regionSearchType(SearchType.CONTAINS)
                .regionField("region")
                .build();

        Pageable pageable = PageRequest.of(offset, size, Sort.by("CompanyName").ascending());
        Page<Customer> customerPage = customerRepository.findAll(CustomerRepository.getSpecification(customerSearch), pageable);

        assertThat(customerPage.getNumberOfElements()).isEqualTo(3);
        assertThat(customerPage.getTotalElements()).isEqualTo(28);
    }

    @Test
    public void findBySpecification_Region_Country() {
//        https://dimitr.im/writing-dynamic-queries-with-spring-data-jpa
//        https://www.logicbig.com/tutorials/spring-framework/spring-data/query-example-matchers.html

        int offset = 0;
        int size = 3;

        CustomerSearch customerSearch = CustomerSearch.builder()
                .regionValue("Western Europe")
                .countryValue("France")
                .regionField("region")
                .countryField("country")
                .regionSearchType(SearchType.IS)
                .countrySearchType(SearchType.IS)
                .build();

        Pageable pageable = PageRequest.of(offset, size, Sort.by("CompanyName").ascending());

        Page<Customer> customerPage = customerRepository.findAll(CustomerRepository.getSpecification(customerSearch), pageable);

        assertThat(customerPage.getNumberOfElements()).isEqualTo(3);
        assertThat(customerPage.getTotalElements()).isEqualTo(11);
    }

    @Test
    public void findBySpecification_City() {
//        https://dimitr.im/writing-dynamic-queries-with-spring-data-jpa
//        https://www.logicbig.com/tutorials/spring-framework/spring-data/query-example-matchers.html

        int offset = 0;
        int size = 3;

        CustomerSearch customerSearch = CustomerSearch.builder()
                .cityValue("Portland")
                .cityField("city")
                .citySearchType(SearchType.IS)
                .build();

        Pageable pageable = PageRequest.of(offset, size, Sort.by("CompanyName").ascending());

        Page<Customer> customerPage = customerRepository.findAll(CustomerRepository.getSpecification(customerSearch), pageable);

        assertThat(customerPage.getNumberOfElements()).isEqualTo(2);
        assertThat(customerPage.getTotalElements()).isEqualTo(2);
    }


    // ==================================================================

    private Customer createCustomer(String id, String name, String country) {
        return Customer.builder().id(id).companyName(name).country(country).build();
    }
}
