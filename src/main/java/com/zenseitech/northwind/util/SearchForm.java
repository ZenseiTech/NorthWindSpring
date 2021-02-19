package com.zenseitech.northwind.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.MultiValueMap;

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
        if (getSort() != null) {
            for (Sort sort : getSort()) {
                pageable = PageRequest.of(getOffset(), getLimit(),
                        org.springframework.data.domain.Sort.by(sort.getField()).descending());
                if (sort.getDirection().equalsIgnoreCase("ASC")) {
                    pageable = PageRequest.of(getOffset(), getLimit(),
                            org.springframework.data.domain.Sort.by(sort.getField()).ascending());
                }
            }
        }
        return pageable;
    }

    public static SearchForm get(MultiValueMap<String, String> paramMap) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String key = paramMap.values().iterator().next().get(0);
        SearchForm searchForm = objectMapper.readValue(key, SearchForm.class);

        searchForm.offset = searchForm.offset / searchForm.limit;
        return searchForm;
    }
}
