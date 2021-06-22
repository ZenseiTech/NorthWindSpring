package com.zenseitech.northwind.productDetail;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDetailsChanges {
    private String recid;
    private String productName;
    private String quantityPerUnit;
    private int unitPrice = -1;
    private int unitsInStock = -1;
    private int unitsOnOrder = -1;
    private int reorderLevel = -1;
    private boolean discontinued;
    private String categoryName;
    private String categoryDescription;
    private String supplierName;
    private String supplierRegion;

    public void update(ProductDetail productDetail) {
        if(getProductName() != null) {
            productDetail.setProductName(getProductName());
        }
        if(getQuantityPerUnit() != null) {
            productDetail.setQuantityPerUnit(getQuantityPerUnit());
        }
        if(getUnitPrice() != -1) {
            productDetail.setUnitPrice(getUnitPrice());
        }
        if(getUnitsInStock() != -1) {
            productDetail.setUnitsInStock(getUnitsInStock());
        }
        if( getUnitsOnOrder() != -1) {
            productDetail.setUnitsOnOrder(getUnitsOnOrder());
        }
        if(getReorderLevel() != -1) {
            productDetail.setReorderLevel(getReorderLevel());
        }
        if(isDiscontinued()) {
            productDetail.setDiscontinued(1);
        } else {
            productDetail.setDiscontinued(0);
        }
        if(getCategoryName() != null) {
            productDetail.setCategoryName(getCategoryName());
        }
        if(getCategoryDescription() != null) {
            productDetail.setCategoryDescription(getCategoryDescription());
        }
        if(getSupplierName() != null) {
            productDetail.setSupplierName(getSupplierName());
        }
        if(getSupplierRegion() != null) {
            productDetail.setSupplierRegion(getSupplierRegion());
        }
    }
}
