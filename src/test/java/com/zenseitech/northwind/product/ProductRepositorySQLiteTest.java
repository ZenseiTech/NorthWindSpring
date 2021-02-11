package com.zenseitech.northwind.product;

import com.zenseitech.northwind.configuration.DataSourceConfiguration;
import com.zenseitech.northwind.customer.Customer;
import com.zenseitech.northwind.customer.CustomerRepository;
import com.zenseitech.northwind.customer.CustomerSearch;
import com.zenseitech.northwind.supplier.Supplier;
import com.zenseitech.northwind.supplier.SupplierRepository;
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

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
@ContextConfiguration(classes = DataSourceConfiguration.class)
@ActiveProfiles(DataSourceConfiguration.PROFILE_SQLITE)
public class ProductRepositorySQLiteTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Test
    public void findBySpecification_Country() {

        int offset = 0;
        int size = 3;
        ProductSearch productSearch = ProductSearch.builder()
                .supplierCompanyNameField("supplierCompanyName")
                .supplierCompanyNameValue("Pavlova, Ltd.")
                .supplierCompanyNameSearchType(SearchType.IS)
                .build();

        Pageable pageable = PageRequest.of(offset, size, Sort.by("ProductName").ascending());
        ProductServiceDefault productService = new ProductServiceDefault(productRepository, supplierRepository);
        Page<Product> productPage = productRepository.findAll(productService.getSpecification(productSearch), pageable);

        assertThat(productPage.getNumberOfElements()).isEqualTo(3);
        assertThat(productPage.getTotalElements()).isEqualTo(5);
    }

}