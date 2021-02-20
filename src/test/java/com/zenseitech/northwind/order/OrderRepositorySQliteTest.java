package com.zenseitech.northwind.order;

import com.zenseitech.northwind.configuration.DataSourceConfiguration;
import com.zenseitech.northwind.customer.CustomerRepository;
import com.zenseitech.northwind.employee.EmployeeRepository;
import com.zenseitech.northwind.shipper.ShipperRepository;
import com.zenseitech.northwind.util.Search;
import com.zenseitech.northwind.util.SearchForm;
import org.junit.Before;
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
public class OrderRepositorySQliteTest {

    @Autowired
    private OrderViewRepository orderViewRepository;

    private OrderServiceDefault orderServiceDefault;

    @Before
    public void setup() {
        orderServiceDefault = new OrderServiceDefault(orderViewRepository);
    }


    @Test
    public void findBySpecification_Employee_FullName() {

        int offset = 0;
        int size = 300;

        SearchForm searchForm = new SearchForm();
        List<Search> searchList = new ArrayList<>();
        Search search = new Search();
        search.setValue("Anne Dodsworth");
        search.setField("employeeFullName");
        search.setOperator("IS");
        search.setType("text");

        searchList.add(search);
        searchForm.setSearch(searchList);

        OrderSearch orderSearch = OrderSearch.get(searchForm);

        Pageable pageable = PageRequest.of(offset, size, Sort.by("RequiredDate").ascending());
        Page<OrderView> orderPage = orderViewRepository.findAll(orderServiceDefault.getSpecification(orderSearch), pageable);

        assertThat(orderPage.getNumberOfElements()).isEqualTo(300);
        assertThat(orderPage.getTotalElements()).isEqualTo(1835);
    }

    @Test
    public void findBySpecification_Employee_FullName_and_OrderDate() {

        int offset = 0;
        int size = 300;

        SearchForm searchForm = new SearchForm();
        List<Search> searchList = new ArrayList<>();
        Search search = new Search();
        search.setValue("Anne Dodsworth");
        search.setField("employeeFullName");
        search.setOperator("IS");
        search.setType("text");

        Search search1 = new Search();
        List<String> betweenDates = new ArrayList<>();
        betweenDates.add("2015-11-10");
        betweenDates.add("2015-11-18");
        search1.setValue(betweenDates);
        search1.setField("orderDate");
        search1.setOperator("BETWEEN");
        search1.setType("date");

        searchList.add(search);
        searchList.add(search1);

        searchForm.setSearch(searchList);

        OrderSearch orderSearch = OrderSearch.get(searchForm);

        Pageable pageable = PageRequest.of(offset, size, Sort.by("RequiredDate").ascending());
        Page<OrderView> orderPage = orderViewRepository.findAll(orderServiceDefault.getSpecification(orderSearch), pageable);

        assertThat(orderPage.getNumberOfElements()).isEqualTo(15);
        assertThat(orderPage.getTotalElements()).isEqualTo(15);

    }


    @Test
    public void findBySpecification_Customer_companyName() {

        int offset = 0;
        int size = 120;

        SearchForm searchForm = new SearchForm();
        List<Search> searchList = new ArrayList<>();
        Search search = new Search();
        search.setValue("Du monde entier");
        search.setField("customerCompanyName");
        search.setOperator("IS");
        search.setType("text");

        searchList.add(search);
        searchForm.setSearch(searchList);

        OrderSearch orderSearch = OrderSearch.get(searchForm);

        Pageable pageable = PageRequest.of(offset, size, Sort.by("RequiredDate").ascending());
        Page<OrderView> orderPage = orderViewRepository.findAll(orderServiceDefault.getSpecification(orderSearch), pageable);

        assertThat(orderPage.getNumberOfElements()).isEqualTo(120);
        assertThat(orderPage.getTotalElements()).isEqualTo(169);
    }


    @Test
    public void findBySpecification_Shipper() {

        int offset = 0;
        int size = 300;

        SearchForm searchForm = new SearchForm();
        List<Search> searchList = new ArrayList<>();
        Search search = new Search();
        search.setValue("Federal Shipping");
        search.setField("shipCompanyName");
        search.setOperator("IS");
        search.setType("text");

        searchList.add(search);
        searchForm.setSearch(searchList);

        OrderSearch orderSearch = OrderSearch.get(searchForm);

        Pageable pageable = PageRequest.of(offset, size, Sort.by("RequiredDate").ascending());
        Page<OrderView> orderPage = orderViewRepository.findAll(orderServiceDefault.getSpecification(orderSearch), pageable);

        assertThat(orderPage.getNumberOfElements()).isEqualTo(300);
        assertThat(orderPage.getTotalElements()).isEqualTo(5654);
    }
}
