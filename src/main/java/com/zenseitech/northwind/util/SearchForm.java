package com.zenseitech.northwind.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Getter
@Setter
@ToString
public class SearchForm {
    private String cmd;
    private int limit;
    private int offset;
    private List<Integer> selected;
    private String searchLogic;
    private List<Search> search;
    private List<Sort> sort;

    public Pageable getPageable() {
        Pageable pageable = PageRequest.of(getOffset(), getLimit());
        if(getSort() != null) {
            for(Sort sort : getSort()) {
                pageable = PageRequest.of(getOffset(), getLimit(),
                        org.springframework.data.domain.Sort.by(sort.getField()).descending());
                if(sort.getDirection().equalsIgnoreCase("ASC")) {
                    pageable = PageRequest.of(getOffset(), getLimit(),
                            org.springframework.data.domain.Sort.by(sort.getField()).ascending());
                }
            }
        }
        return pageable;
    }
}
