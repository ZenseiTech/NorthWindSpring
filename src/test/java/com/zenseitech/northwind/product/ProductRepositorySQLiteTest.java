package com.zenseitech.northwind.product;

import com.zenseitech.northwind.category.CategoryRepository;
import com.zenseitech.northwind.configuration.DataSourceConfiguration;
import com.zenseitech.northwind.supplier.SupplierRepository;
import com.zenseitech.northwind.util.Search;
import com.zenseitech.northwind.util.SearchForm;
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

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void find_By_Supplier() {

        int offset = 0;
        int size = 3;

        SearchForm searchForm = new SearchForm();
        Search search = new Search();
        search.setField("supplierCompanyName");
        search.setOperator("is");
        search.setType("text");
        search.setValue("Pavlova, Ltd.");
        List<Search> searchList = new ArrayList<>();
        searchList.add(search);
        searchForm.setSearch(searchList);

        ProductSearch productSearch = ProductSearch.get(searchForm);

        Pageable pageable = PageRequest.of(offset, size, Sort.by("ProductName").ascending());
        ProductServiceDefault productService = new ProductServiceDefault(
                                            productRepository,
                                            supplierRepository,
                                            categoryRepository);
        Page<Product> productPage = productRepository.findAll(productService.getSpecification(productSearch), pageable);

        assertThat(productPage.getNumberOfElements()).isEqualTo(3);
        assertThat(productPage.getTotalElements()).isEqualTo(5);
    }

    @Test
    public void find_By_Category() {

        int offset = 0;
        int size = 3;

        SearchForm searchForm = new SearchForm();
        Search search = new Search();
        search.setField("categoryName");
        search.setOperator("is");
        search.setType("text");
        search.setValue("Dairy Products");
        List<Search> searchList = new ArrayList<>();
        searchList.add(search);
        searchForm.setSearch(searchList);

        ProductSearch productSearch = ProductSearch.get(searchForm);

        Pageable pageable = PageRequest.of(offset, size, Sort.by("ProductName").ascending());
        ProductServiceDefault productService = new ProductServiceDefault(
                                                    productRepository,
                                                    supplierRepository,
                                                    categoryRepository);
        Page<Product> productPage = productRepository.findAll(productService.getSpecification(productSearch), pageable);

        assertThat(productPage.getNumberOfElements()).isEqualTo(3);
        assertThat(productPage.getTotalElements()).isEqualTo(10);
    }


    @Test
    public void find_By_Category_Supplier() {

        int offset = 0;
        int size = 3;

        SearchForm searchForm = new SearchForm();
        List<Search> searchList = new ArrayList<>();

        Search searchCategory = new Search();
        searchCategory.setField("categoryName");
        searchCategory.setOperator("is");
        searchCategory.setType("text");
        searchCategory.setValue("Dairy Products");

        searchList.add(searchCategory);

        Search searchSupplier = new Search();
        searchSupplier.setField("supplierCompanyName");
        searchSupplier.setOperator("is");
        searchSupplier.setType("text");
        searchSupplier.setValue("Norske Meierier");
        searchList.add(searchSupplier);

        searchForm.setSearch(searchList);

        ProductSearch productSearch = ProductSearch.get(searchForm);

        Pageable pageable = PageRequest.of(offset, size, Sort.by("ProductName").ascending());
        ProductServiceDefault productService = new ProductServiceDefault(
                productRepository,
                supplierRepository,
                categoryRepository);
        Page<Product> productPage = productRepository.findAll(productService.getSpecification(productSearch), pageable);

        assertThat(productPage.getNumberOfElements()).isEqualTo(3);
        assertThat(productPage.getTotalElements()).isEqualTo(3);
    }

    @Test
    public void find_By_ProductName_Contains() {

        int offset = 0;
        int size = 10;

        SearchForm searchForm = new SearchForm();
        List<Search> searchList = new ArrayList<>();

        Search searchProductName = new Search();
        searchProductName.setField("productName");
        searchProductName.setOperator("contains");
        searchProductName.setType("text");
        searchProductName.setValue("G");
        searchList.add(searchProductName);

        searchForm.setSearch(searchList);

        ProductSearch productSearch = ProductSearch.get(searchForm);

        Pageable pageable = PageRequest.of(offset, size, Sort.by("ProductName").ascending());
        ProductServiceDefault productService = new ProductServiceDefault(
                productRepository,
                supplierRepository,
                categoryRepository);
        Page<Product> productPage = productRepository.findAll(productService.getSpecification(productSearch), pageable);

        assertThat(productPage.getNumberOfElements()).isEqualTo(10);
        assertThat(productPage.getTotalElements()).isEqualTo(36);
    }

    @Test
    public void find_By_ProductName_StartWith() {

        int offset = 0;
        int size = 10;

        SearchForm searchForm = new SearchForm();
        List<Search> searchList = new ArrayList<>();

        Search searchProductName = new Search();
        searchProductName.setField("productName");
        searchProductName.setOperator("begins");
        searchProductName.setType("text");
        searchProductName.setValue("G");
        searchList.add(searchProductName);

        searchForm.setSearch(searchList);

        ProductSearch productSearch = ProductSearch.get(searchForm);

        Pageable pageable = PageRequest.of(offset, size, Sort.by("ProductName").ascending());
        ProductServiceDefault productService = new ProductServiceDefault(
                productRepository,
                supplierRepository,
                categoryRepository);
        Page<Product> productPage = productRepository.findAll(productService.getSpecification(productSearch), pageable);

        assertThat(productPage.getNumberOfElements()).isEqualTo(10);
        assertThat(productPage.getTotalElements()).isEqualTo(11);
    }

    @Test
    public void find_By_ProductName_EndWith() {

        int offset = 0;
        int size = 10;

        SearchForm searchForm = new SearchForm();
        List<Search> searchList = new ArrayList<>();

        Search searchProductName = new Search();
        searchProductName.setField("productName");
        searchProductName.setOperator("ends");
        searchProductName.setType("text");
        searchProductName.setValue("E");
        searchList.add(searchProductName);

        searchForm.setSearch(searchList);

        ProductSearch productSearch = ProductSearch.get(searchForm);

        Pageable pageable = PageRequest.of(offset, size, Sort.by("ProductName").ascending());
        ProductServiceDefault productService = new ProductServiceDefault(
                productRepository,
                supplierRepository,
                categoryRepository);
        Page<Product> productPage = productRepository.findAll(productService.getSpecification(productSearch), pageable);

        assertThat(productPage.getNumberOfElements()).isEqualTo(10);
        assertThat(productPage.getTotalElements()).isEqualTo(17);
    }

}