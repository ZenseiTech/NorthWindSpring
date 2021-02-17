package com.zenseitech.northwind.productDetail;

import com.zenseitech.northwind.configuration.DataSourceConfiguration;
import com.zenseitech.northwind.util.Search;
import com.zenseitech.northwind.util.SearchForm;
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
public class ProductDetailRepositorySQLiteTest {

    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Test
    public void find_By_Supplier() {

        int offset = 0;
        int size = 3;

        SearchForm searchForm = new SearchForm();
        Search search = new Search();
        search.setField("supplierName");
        search.setOperator("is");
        search.setType("text");
        search.setValue("Pavlova, Ltd.");
        List<Search> searchList = new ArrayList<>();
        searchList.add(search);
        searchForm.setSearch(searchList);

        ProductDetailSearch productDetailSearch = ProductDetailSearch.get(searchForm);

        Pageable pageable = PageRequest.of(offset, size, Sort.by("ProductName").ascending());
        ProductDetailServiceDefault productDetailService = new ProductDetailServiceDefault(
                productDetailRepository);
        Page<ProductDetail> productDetailPage = productDetailRepository.findAll(productDetailService.getSpecification(productDetailSearch), pageable);

        assertThat(productDetailPage.getNumberOfElements()).isEqualTo(3);
        assertThat(productDetailPage.getTotalElements()).isEqualTo(5);

        System.out.println();
//        System.out.println(productDetailPage.getContent());
        productDetailPage.getContent().forEach(e -> System.out.println("==> " + e.toString()));
        System.out.println();
    }

}