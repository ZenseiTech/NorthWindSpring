package com.zenseitech.northwind.order;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "Order_V")
@Data
@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderView {

    public OrderView() {

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

    private String employeeFullName;
    private String customerCompanyName;
    private String shipCompanyName;

    /**
     * w2ui unique required value
     * @return
     */
    public BigDecimal getRecid() {
        return id;
    }

}
