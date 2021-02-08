package com.zenseitech.northwind.customer;

import com.zenseitech.northwind.configuration.DataSourceConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
@ContextConfiguration(classes = DataSourceConfiguration.class)
@ActiveProfiles(DataSourceConfiguration.PROFILE_SQLITE)
public class CustomerServiceDefaultTest {

    @Autowired
    private CustomerRepository customerRepository;
    private CustomerService customerService;

    @Before
    public void setUp() {
        customerService = new CustomerServiceDefault(customerRepository);
    }

    @Test
    public void search() {
        int offset = 1;
        int size = 3;
        Pageable pageable = PageRequest.of(offset, size, Sort.by("CompanyName").ascending());
        Page<Customer> customerPage = customerService.search(pageable);
        assertThat(customerPage.getNumberOfElements()).isEqualTo(3);
        assertThat(customerPage.getTotalElements()).isEqualTo(91);
    }

    @Test
    public void searchByCountry() {

        int offset = 1;
        int size = 3;

        Pageable pageable = PageRequest.of(offset, size, Sort.by("CompanyName").ascending());

        Customer customer = Customer.builder()
                .country("USA")
                .build();
        Specification<Customer> specification = Specification
                .where(customer.getCountry() == null ? null : CustomerRepository.countryContains(customer.getCountry()))
                .and(customer.getRegion() == null ? null : CustomerRepository.regionContains(customer.getRegion()));

        Page<Customer> customerPage = customerService.search(specification, pageable);

        assertThat(customerPage.getNumberOfElements()).isEqualTo(3);
        assertThat(customerPage.getTotalElements()).isEqualTo(13);

        for(Customer customer1 : customerPage.getContent()) {
            System.out.println("===> " + customer1.getId());
        }
//        System.out.println("----> " + customerPage.getContent().toString());
    }
}