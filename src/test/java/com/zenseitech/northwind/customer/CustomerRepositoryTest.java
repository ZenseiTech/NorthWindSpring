package com.zenseitech.northwind.customer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
// Use so the test that insert/update are committed from test to test ...
@Transactional
// No longer required since SpringBoot take care of it now ...
//@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Before
    public void setUp() {
        customerRepository.save(createCustomer("1", "customer1", "Mexico"));
        customerRepository.save(createCustomer("2", "customer2", "Mexico"));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void savesCustomerCorrectly() {

        Iterable<Customer> customers = customerRepository.findAll();
        assertThat(customers).hasSize(2);

        // having ...
        Customer newCustomer = createCustomer("3", "customer3", "Mexico");

        // when ...
        Customer savedCustomer = customerRepository.save(newCustomer);

        // then ...
        assertThat(savedCustomer.toString()).isEqualTo(newCustomer.toString());
        assertThat(customerRepository.count()).isEqualTo(3);
    }

    @Test
    public void notFoundCustomer() {
        Iterable<Customer> customers = customerRepository.findAll();
        assertThat(customers).hasSize(2);

        Customer notFoundCustomer = new Customer();
        notFoundCustomer.setCompanyName("Not Found");

        Customer customer = customerRepository.findById("2").orElse(notFoundCustomer);
        assertThat(customer.getId()).isEqualTo("2");

        Customer customer1 = customerRepository.findById("3").orElse(notFoundCustomer);
        assertThat(customer1.getCompanyName()).isEqualTo(notFoundCustomer.getCompanyName());
    }

    @Test
    public void findByCountry() {
        // https://www.baeldung.com/spring-data-jpa-pagination-sorting
        Pageable pageable = PageRequest.of(0, 1, Sort.by("CompanyName").descending());
        Page customerPage = customerRepository.findByCountry("Mexico", pageable);

        assertThat(customerPage.getNumberOfElements()).isEqualTo(1);
        assertThat(customerPage.getTotalElements()).isEqualTo(2);
    }


    // ==================================================================

    private Customer createCustomer(String id, String name, String country) {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setCompanyName(name);
        customer.setCountry(country);
        return customer;
    }
}