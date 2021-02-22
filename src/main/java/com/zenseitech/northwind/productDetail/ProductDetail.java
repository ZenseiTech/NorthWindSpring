package com.zenseitech.northwind.productDetail;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "ProductDetails_V")
@Data
@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductDetail {

    public ProductDetail() {

    }

    @Id
    private BigDecimal id;

    private String productName;
    private String quantityPerUnit;
    private int unitPrice;
    private int unitsInStock;
    private int unitsOnOrder;
    private int reorderLevel;
    private int discontinued;
    private String categoryName;
    private String categoryDescription;
    private String supplierName;
    private String supplierRegion;

    /**
     * w2ui unique required value
     * @return
     */
    public BigDecimal getRecid() {
        return id;
    }
}
