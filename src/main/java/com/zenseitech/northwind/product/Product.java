package com.zenseitech.northwind.product;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
@Builder
//@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Product {

    public Product() {

    }

    @Id
    private String id;

    @Column(nullable = false, unique = true)
    private String productName;
    private int supplierId = -1;
    private int categoryId = -1;
    private String quantityPerUnit;
    private BigDecimal unitPrice;
    private int unitsInStock = -1;
    private int unitsOnOrder = -1;
    private int reorderLevel = -1;
    private int discontinued = -1;
}
