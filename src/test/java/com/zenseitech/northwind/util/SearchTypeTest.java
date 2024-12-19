package com.zenseitech.northwind.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SearchTypeTest {
    @Test
    public void checkCustomerSearch() {
        assertEquals(SearchType.MORE, SearchType.valueOf("MORE"));
    }
}
