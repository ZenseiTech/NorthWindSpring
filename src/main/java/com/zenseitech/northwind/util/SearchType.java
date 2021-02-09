package com.zenseitech.northwind.util;

public enum SearchType {
    IS("is"),
    BEGINS("begins"),
    CONTAINS("contains"),
    ENDS("ends");

    private final String searchId;

    SearchType(String searchId) {
        this.searchId = searchId;
    }
}
