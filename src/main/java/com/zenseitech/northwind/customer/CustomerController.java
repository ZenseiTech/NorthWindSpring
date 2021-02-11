package com.zenseitech.northwind.customer;

import com.zenseitech.northwind.util.RecordDomain;
import com.zenseitech.northwind.util.SearchForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

    Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/customers")
    public RecordDomain searchCustomers(@RequestBody SearchForm searchForm) {
        logger.debug("====> " + searchForm.toString());
        Pageable pageable = searchForm.getPageable();
        Page<Customer> customerPage;
        RecordDomain recordDomain = new RecordDomain();
        recordDomain.setStatus("success");

        if(searchForm.getSearch() == null) {
            customerPage = customerService.search(pageable);
        } else {
            CustomerSearch customerSearch = CustomerSearch.get(searchForm);
            customerPage = customerService.search(customerSearch, pageable);
        }

        recordDomain.setTotal(customerPage.getTotalElements());
        recordDomain.setRecords(List.class.cast(customerPage.getContent()));
        return recordDomain;
    }
}
