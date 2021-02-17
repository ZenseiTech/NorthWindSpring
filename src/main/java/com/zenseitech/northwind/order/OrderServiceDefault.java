package com.zenseitech.northwind.order;

import com.zenseitech.northwind.customer.Customer;
import com.zenseitech.northwind.customer.CustomerRepository;
import com.zenseitech.northwind.employee.Employee;
import com.zenseitech.northwind.employee.EmployeeRepository;
import com.zenseitech.northwind.shipper.Shipper;
import com.zenseitech.northwind.shipper.ShipperRepository;
import com.zenseitech.northwind.util.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceDefault implements OrderService {

    private final OrderRepository orderRepository;
    private final ShipperRepository shipperRepository;
    private final EmployeeRepository employeeRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public OrderServiceDefault(OrderRepository orderRepository,
                               ShipperRepository shipperRepository,
                               EmployeeRepository employeeRepository,
                               CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.shipperRepository = shipperRepository;
        this.employeeRepository = employeeRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public Page<Order> search(Pageable pageable) {
        return this.orderRepository.findAll(pageable);
    }

    @Override
    public Page<Order> search(OrderSearch orderSearch, Pageable pageable) {
        Specification<Order> specification = getSpecification(orderSearch);
        return this.orderRepository.findAll(specification, pageable);
    }

    protected Specification<Order> getSpecification(OrderSearch orderSearch) {
        Specification<Object> specification = Specification
                .where(Search.getIntegerSpecification(orderSearch.getIdValue(), orderSearch.getIdField(), orderSearch.getIdSearchType()))

                .and(Search.getDateStringSpecification(orderSearch.getOrderDateValue(), orderSearch.getOrderDateField(), orderSearch.getOrderDateSearchType()))

                .and(Search.getIntegerSpecification(getEmployeeId(orderSearch.getEmployeeFullNameValue()), orderSearch.getEmployeeFullNameField(), orderSearch.getEmployeeFullNameSearchType()))

                .and(Search.getStringSpecification(orderSearch.getRequiredDateValue(), orderSearch.getRequiredDateField(), orderSearch.getRequiredDateSearchType()))

                .and(Search.getStringSpecification(orderSearch.getShippedDateValue(), orderSearch.getShippedDateField(), orderSearch.getShippedDateSearchType()))

                .and(Search.getStringSpecification(orderSearch.getShipNameValue(), orderSearch.getShipNameField(), orderSearch.getShipNameSearchType()))

                .and(Search.getIntegerSpecification(getShipperId(orderSearch.getShipCompanyNameValue()), orderSearch.getShipCompanyNameField(), orderSearch.getShipCompanyNameSearchType()))

                .and(Search.getStringSpecification(getCustomerId(orderSearch.getCustomerCompanyNameValue()), orderSearch.getCustomerCompanyNameField(), orderSearch.getCustomerCompanyNameSearchType()))

                .and(Search.getBigDecimalSpecification(orderSearch.getFreightValue(), orderSearch.getIdField(), orderSearch.getIdSearchType()))

                .and(Search.getStringSpecification(orderSearch.getShipAddressValue(), orderSearch.getShipAddressField(), orderSearch.getShipAddressSearchType()))

                .and(Search.getStringSpecification(orderSearch.getShipCityValue(), orderSearch.getShipCityField(), orderSearch.getShipCitySearchType()))

                .and(Search.getStringSpecification(orderSearch.getShipRegionValue(), orderSearch.getShipRegionField(), orderSearch.getShipRegionSearchType()))

                .and(Search.getStringSpecification(orderSearch.getShipPostalCodeValue(), orderSearch.getShipPostalCodeField(), orderSearch.getShipPostalCodeSearchType()))

                .and(Search.getStringSpecification(orderSearch.getShipCountryValue(), orderSearch.getShipCountryField(), orderSearch.getShipCountrySearchType()))
                ;
        return Specification.class.cast(specification);
    }

    private List<Integer> getShipperId(String companyName) {
        List<Integer> shipperIds = new ArrayList<>();
        if(companyName != null) {
            Shipper shipper = findByCompanyName(companyName);
            shipperIds.add(shipper.getId());
        }
        return shipperIds;
    }

    private Shipper findByCompanyName(String companyName) {
        return shipperRepository.findByCompanyName(companyName);
    }


    private List<Integer> getEmployeeId(String employeeFullName) {
        List<Integer> employeeIds = new ArrayList<>();
        if(employeeFullName != null) {
            Employee employee = findByEmployeeFullName(employeeFullName);
            employeeIds.add(employee.getId());
        }
        return employeeIds;
    }

    private Employee findByEmployeeFullName(String employeeFullName) {
        Employee employee = new Employee();
        employee.setFromFullName(employeeFullName);
        return employeeRepository.findByFirstAndLastName(employee.getFirstName(), employee.getLastName());
    }

    private String getCustomerId(String companyName) {
        if(companyName != null) {
            Customer customer = findByCustomerCompanyName(companyName);
            return customer.getId();
        }
        return null;
    }

    private Customer findByCustomerCompanyName(String companyName) {
        return customerRepository.findByCompanyName(companyName);
    }
}
