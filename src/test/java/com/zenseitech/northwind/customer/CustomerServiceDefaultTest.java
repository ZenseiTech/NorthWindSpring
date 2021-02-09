package com.zenseitech.northwind.customer;

import com.zenseitech.northwind.util.SearchType;
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
        CustomerSearch customerSearch = CustomerSearch.builder().customer(customer).build();
        Page<Customer> customerPage = customerService.search(CustomerRepository.getSpecification(customerSearch), pageable);

        assertThat(customerPage.getNumberOfElements()).isEqualTo(3);
        assertThat(customerPage.getTotalElements()).isEqualTo(13);

        for(Customer customer1 : customerPage.getContent()) {
            System.out.println("===> " + customer1.getId());
        }
//        System.out.println("----> " + customerPage.getContent().toString());
    }

    @Test
    public void searchByCountry_City() {

        int offset = 0;
        int size = 3;

        Pageable pageable = PageRequest.of(offset, size, Sort.by("CompanyName").ascending());

        Customer customer = Customer.builder()
                .country("USA")
                .city("Portland")
                .contactTitle("Sales")
                .build();
        CustomerSearch customerSearch = CustomerSearch.builder().
                customer(customer).
                contactTitleSearchType(SearchType.CONTAINS).
                build();
        Page<Customer> customerPage = customerService.search(CustomerRepository.getSpecification(customerSearch), pageable);

        assertThat(customerPage.getNumberOfElements()).isEqualTo(1);
        assertThat(customerPage.getTotalElements()).isEqualTo(1);

        for(Customer customer1 : customerPage.getContent()) {
            System.out.println("===> " + customer1.getId());
        }
//        System.out.println("----> " + customerPage.getContent().toString());
    }
}