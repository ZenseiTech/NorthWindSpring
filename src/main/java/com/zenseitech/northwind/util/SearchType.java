package com.zenseitech.northwind.util;

public enum SearchType {
    IS("is"),
    BEGINS("begins"),
    CONTAINS("contains"),
    ENDS("ends"),
    BETWEEN("between"),
    LESS("less than"),
    MORE("more than"),
    BEFORE("before"),
    AFTER("after");

    private final String searchId;

    SearchType(String searchId) {
        this.searchId = searchId;
    }
}
