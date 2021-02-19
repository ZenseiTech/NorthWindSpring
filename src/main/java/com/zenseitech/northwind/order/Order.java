package com.zenseitech.northwind.order;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.zenseitech.northwind.customer.Customer;
import com.zenseitech.northwind.employee.Employee;
import com.zenseitech.northwind.shipper.Shipper;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "'Order'")
@Data
@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Order {

    public Order() {

    }

    @Id
    private BigDecimal id;

    private String orderDate;
    private String requiredDate;
    private String shippedDate;
    private BigDecimal freight;
    private String shipName;
    private String shipAddress;
    private String shipCity;
    private String shipRegion;
    private String shipPostalCode;
    private String shipCountry;

    private String customerId;

    @ManyToOne
    @JoinColumn(name="employeeId", nullable = false)
    @JsonBackReference
    private Employee employee;

    public String getEmployeeFullName() {
        return employee.getFullName();
    }

    @ManyToOne
    @JoinColumn(name="shipVia", nullable = false)
    @JsonBackReference
    private Shipper shipper;

    public String getShipperCompanyName() {
        return shipper.getCompanyName();
    }

    @ManyToOne
    @JoinColumn(name="customerId", nullable = false, insertable = false, updatable = false)
    @JsonBackReference
    private Customer customer;

    public String getCustomerCompanyName() {
        return customer.getCompanyName();
    }

    /**
     * w2ui unique required value
     * @return
     */
    public BigDecimal getRecid() {
        return id;
    }
}
