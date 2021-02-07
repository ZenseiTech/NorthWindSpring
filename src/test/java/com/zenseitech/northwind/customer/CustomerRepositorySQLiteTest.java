package com.zenseitech.northwind.customer;

import com.zenseitech.northwind.configuration.DataSourceConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
}
