package com.zenseitech.northwind.product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.zenseitech.northwind.category.Category;
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
    private String quantityPerUnit;
    private BigDecimal unitPrice;
    private int unitsInStock;
    private int unitsOnOrder;
    private int reorderLevel;
    private int discontinued;

    @ManyToOne
    @JoinColumn(name="supplierId", nullable = false)
    @JsonBackReference
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name="categoryId", nullable = false)
    @JsonBackReference
    private Category category;

    public String getSupplierCompanyName() {
        return supplier.getCompanyName();
    }

    public String getCategoryName() {
        return category.getCategoryName();
    }

    /**
     * w2ui unique required value
     * @return
     */
    public BigDecimal getRecId() {
        return id;
    }
}
