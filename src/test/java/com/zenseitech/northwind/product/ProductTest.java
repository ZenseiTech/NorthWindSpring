package com.zenseitech.northwind.product;

import com.zenseitech.northwind.orderdetail.OrderDetail;
import org.junit.Assert;
import org.junit.Test;

import java.beans.Expression;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class ProductTest {
    @Test
    public void setterCheck() {

        // having ...
        Map<String, Object> setToValue = new HashMap<>();
        setToValue.put("setProductName", "Vodka");
        setToValue.put("setUnitPrice", new BigDecimal(20.02));

        // when ...
        Product product = new Product();
        setToValue.forEach( (key, value) -> {
            Expression expression = new Expression(product, key, new Object [] {value});
            try {
                expression.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        // then ...
        assertEquals("Vodka", product.getProductName());
        assertEquals(new BigDecimal(20.02), product.getUnitPrice());
    }
}