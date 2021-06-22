package com.zenseitech.northwind.orderdetail;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.zenseitech.northwind.product.Product;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "OrderDetail")
@Data
@ToString
public class OrderDetail {

    public OrderDetail() {
    }

    @Id
    private String id;
    private BigDecimal orderId;
    private BigDecimal unitPrice;
    private BigDecimal quantity;
    private BigDecimal discount;

    @ManyToOne
    @JoinColumn(name="productId", nullable = false)
    @JsonBackReference
    private Product product;

    public String getProductName() {
        return product.getProductName();
    }

    /**
     * w2ui unique required value for the Grid
     * @return
     */
    public String getRecid() {
        return id;
    }
}
