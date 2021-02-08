package com.zenseitech.northwind.customer;

import com.zenseitech.northwind.SearchType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/")
    public List<Customer> searchCustomers() {
        Customer customer = Customer.builder().country("USA").build();
        CustomerSearch customerSearch = CustomerSearch.builder()
                .customer(customer)
                .countrySearchType(SearchType.CONTAINS).build();
        Pageable pageable = PageRequest.of(0, 3, Sort.by("CompanyName").ascending());
        Page<Customer> customerPage = customerService.search(CustomerRepository.getSpecification(customerSearch), pageable);
        return customerPage.getContent();
    }
}
