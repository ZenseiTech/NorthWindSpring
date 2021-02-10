package com.zenseitech.northwind.product;

import com.zenseitech.northwind.configuration.DataSourceConfiguration;
import com.zenseitech.northwind.customer.Customer;
import com.zenseitech.northwind.customer.CustomerRepository;
import com.zenseitech.northwind.customer.CustomerSearch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
@ContextConfiguration(classes = DataSourceConfiguration.class)
@ActiveProfiles(DataSourceConfiguration.PROFILE_SQLITE)
public class ProductRepositorySQLiteTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void findBySpecification_Country() {

        int offset = 0;
        int size = 3;
        Product product = new Product();
        product.setSupplierId(12);

        ProductSearch productSearch = ProductSearch.builder()
                .product(product)
                .supplierIdField("supplierId")
                .build();

        Pageable pageable = PageRequest.of(offset, size, Sort.by("ProductName").ascending());
        Page<Product> productPage = productRepository.findAll(ProductRepository.getSpecification(productSearch), pageable);

        assertThat(productPage.getNumberOfElements()).isEqualTo(3);
        assertThat(productPage.getTotalElements()).isEqualTo(5);
    }

}