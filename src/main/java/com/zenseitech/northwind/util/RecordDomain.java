package com.zenseitech.northwind.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class RecordDomain {
    private String status;
    private long total;

    private List<Object> records = new ArrayList<>();
}
