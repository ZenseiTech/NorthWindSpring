package com.zenseitech.northwind.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Sort {
    private String field;
    private String direction;
}
