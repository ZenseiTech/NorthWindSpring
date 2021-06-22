package com.zenseitech.northwind.productDetail;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zenseitech.northwind.util.SearchForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.MultiValueMap;

import java.util.List;

@Getter
@Setter
@ToString
public class ProductDetailSearchForm extends SearchForm {

    private List<ProductDetailsChanges> changes;

    public static ProductDetailSearchForm get(MultiValueMap<String, String> paramMap) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String key = paramMap.values().iterator().next().get(0);
        ProductDetailSearchForm searchForm = objectMapper.readValue(key, ProductDetailSearchForm.class);

        if(searchForm.getOffset() != 0) {
            searchForm.setOffset(searchForm.getOffset() / searchForm.getOffset());
        }
        return searchForm;
    }
}
