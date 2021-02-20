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

    private final OrderViewRepository orderViewRepository;

    @Autowired
    public OrderServiceDefault(OrderViewRepository orderViewRepository) {
        this.orderViewRepository = orderViewRepository;
    }

    @Override
    public Page<OrderView> search(Pageable pageable) {
        return this.orderViewRepository.findAll(pageable);
    }

    @Override
    public Page<OrderView> search(OrderSearch orderSearch, Pageable pageable) {
        Specification<OrderView> specification = getSpecification(orderSearch);
        return this.orderViewRepository.findAll(specification, pageable);
    }

    protected Specification<OrderView> getSpecification(OrderSearch orderSearch) {
        Specification<Object> specification = Specification
                .where(Search.getIntegerSpecification(orderSearch.getIdValue(), orderSearch.getIdField(), orderSearch.getIdSearchType()))

                .and(Search.getDateStringSpecification(orderSearch.getOrderDateValue(), orderSearch.getOrderDateField(), orderSearch.getOrderDateSearchType()))

                .and(Search.getStringSpecification(orderSearch.getEmployeeFullNameValue(), orderSearch.getEmployeeFullNameField(), orderSearch.getEmployeeFullNameSearchType()))

                .and(Search.getStringSpecification(orderSearch.getRequiredDateValue(), orderSearch.getRequiredDateField(), orderSearch.getRequiredDateSearchType()))

                .and(Search.getStringSpecification(orderSearch.getShippedDateValue(), orderSearch.getShippedDateField(), orderSearch.getShippedDateSearchType()))

                .and(Search.getStringSpecification(orderSearch.getShipNameValue(), orderSearch.getShipNameField(), orderSearch.getShipNameSearchType()))

                .and(Search.getStringSpecification(orderSearch.getShipCompanyNameValue(), orderSearch.getShipCompanyNameField(), orderSearch.getShipCompanyNameSearchType()))

                .and(Search.getStringSpecification(orderSearch.getCustomerCompanyNameValue(), orderSearch.getCustomerCompanyNameField(), orderSearch.getCustomerCompanyNameSearchType()))

                .and(Search.getStringSpecification(orderSearch.getCustomerIdValue(), orderSearch.getCustomerIdField(), orderSearch.getCustomerIdSearchType()))

                .and(Search.getBigDecimalSpecification(orderSearch.getFreightValue(), orderSearch.getFreightField(), orderSearch.getFreightSearchType()))

                .and(Search.getStringSpecification(orderSearch.getShipAddressValue(), orderSearch.getShipAddressField(), orderSearch.getShipAddressSearchType()))

                .and(Search.getStringSpecification(orderSearch.getShipCityValue(), orderSearch.getShipCityField(), orderSearch.getShipCitySearchType()))

                .and(Search.getStringSpecification(orderSearch.getShipRegionValue(), orderSearch.getShipRegionField(), orderSearch.getShipRegionSearchType()))

                .and(Search.getStringSpecification(orderSearch.getShipPostalCodeValue(), orderSearch.getShipPostalCodeField(), orderSearch.getShipPostalCodeSearchType()))

                .and(Search.getStringSpecification(orderSearch.getShipCountryValue(), orderSearch.getShipCountryField(), orderSearch.getShipCountrySearchType()))
                ;
        return Specification.class.cast(specification);
    }
}
