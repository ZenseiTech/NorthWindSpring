package com.zenseitech.northwind.product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.zenseitech.northwind.supplier.Supplier;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Product")
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
    private BigDecimal id;

    @Column(nullable = false, unique = true)
    private String productName;
//    private int supplierId = -1;
    private int categoryId = -1;
    private String quantityPerUnit;
    private BigDecimal unitPrice;
    private int unitsInStock = -1;
    private int unitsOnOrder = -1;
    private int reorderLevel = -1;
    private int discontinued = -1;

    @ManyToOne
    @JoinColumn(name="supplierId", nullable = false)
//    @JsonBackReference
    private Supplier supplier;

    /**
     * w2ui unique required value
     * @return
     */
    public BigDecimal getRecId() {
        return id;
    }
}
