package com.zenseitech.northwind.customer;

import com.zenseitech.northwind.configuration.DataSourceConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

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
//        Iterable<Customer> customers = customerRepository.findAll();
//        assertThat(customers).hasSize(2);
        assertThat(customerRepository.count()).isEqualTo(91);

        Customer notFoundCustomer = new Customer();
        notFoundCustomer.setCompanyName("Not Found");
        Customer customer = customerRepository.findById("AROUT").orElse(notFoundCustomer);

        assertThat("AROUT").isEqualTo(customer.getId());

        Customer customer1 = customerRepository.findById("AROU").orElse(notFoundCustomer);
        assertThat(customer1.getCompanyName()).isEqualTo(notFoundCustomer.getCompanyName());
    }

    @Test
    public void findByCountry() {
        // https://www.baeldung.com/spring-data-jpa-pagination-sorting
        int offset = 0;
        int size = 3;
        String country = "USA";
        Pageable pageable = PageRequest.of(offset, size, Sort.by("CompanyName").ascending());
        Page customerPage = customerRepository.findByCountry(country, pageable);
        assertThat(customerPage.getNumberOfElements()).isEqualTo(3);
        assertThat(customerPage.getTotalElements()).isEqualTo(13);
//        System.out.println("----> " + customerPage.getContent().toString());
    }
}
