package com.zenseitech.northwind.product;

import com.zenseitech.northwind.configuration.DataSourceConfiguration;
import com.zenseitech.northwind.customer.Customer;
import com.zenseitech.northwind.customer.CustomerRepository;
import com.zenseitech.northwind.customer.CustomerSearch;
import com.zenseitech.northwind.supplier.Supplier;
import com.zenseitech.northwind.util.SearchType;
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

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
        List<Integer> values = new ArrayList<>();
        values.add(12);

        ProductSearch productSearch = ProductSearch.builder()
                .supplierIdField("supplier")
                .supplierIdValue(values)
                .supplierIdSearchType(SearchType.IS)
                .build();

        Pageable pageable = PageRequest.of(offset, size, Sort.by("ProductName").ascending());
        Page<Product> productPage = productRepository.findAll(ProductRepository.getSpecification(productSearch), pageable);

        assertThat(productPage.getNumberOfElements()).isEqualTo(3);
        assertThat(productPage.getTotalElements()).isEqualTo(5);
    }

}