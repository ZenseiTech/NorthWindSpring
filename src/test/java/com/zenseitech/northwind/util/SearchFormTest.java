package com.zenseitech.northwind.util;

import com.zenseitech.northwind.customer.Customer;
import com.zenseitech.northwind.customer.CustomerSearch;
import org.junit.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchFormTest {

    @Test
    public void checkCustomerSearch() {
        SearchForm searchForm = getForm();

        CustomerSearch customerSearch = CustomerSearch.get(searchForm);
        Pageable pageable = searchForm.getPageable();

        assertThat(customerSearch.getCustomer().getCity()).isEqualTo("Seatle");
        assertThat(pageable.getSort().getOrderFor("city").isAscending()).isTrue();
    }

    private SearchForm getForm() {
        SearchForm searchForm = new SearchForm();
        Search search = new Search();
        search.setField("city");
        search.setOperator("is");
        search.setType("Text");
        search.setValue("Seatle");

        Sort sort = new Sort();
        sort.setDirection("ASC");
        sort.setField("city");

        List<Search> searchList = new ArrayList<>();
        searchList.add(search);

        List<Sort> sortList = new ArrayList<>();
        sortList.add(sort);

        searchForm.setSearch(searchList);
        searchForm.setSort(sortList);

        searchForm.setCmd("cmd");
        searchForm.setLimit(10);
        searchForm.setOffset(0);
        searchForm.setSearchLogic("AND");

        List<Integer> selected = new ArrayList<>();
        selected.add(10);
        selected.add(20);
        searchForm.setSelected(selected);

        return searchForm;
    }

}