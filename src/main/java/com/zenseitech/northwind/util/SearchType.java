package com.zenseitech.northwind.util;

public enum SearchType {
    IS("is"),
    BEGINS("begins"),
    CONTAINS("contains"),
    ENDS("ends"),
    BETWEEN("between"),
    LESS_THAN("less than"),
    MORE_THAN("more than"),
    BEFORE("before"),
    AFTER("after")
    ;

    private final String searchId;

    SearchType(String searchId) {
        this.searchId = searchId;
    }
}
