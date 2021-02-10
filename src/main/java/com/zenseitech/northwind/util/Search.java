package com.zenseitech.northwind.util;

import com.zenseitech.northwind.customer.Customer;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.Specification;

import java.text.MessageFormat;

@Getter
@Setter
@ToString
public class Search {
    private String field;
    private String type;
    private Object value;
    private String operator;

    public static Specification<Customer> getStringSpecification(String expression, String field, SearchType searchType) {
        if(searchType == null) {
            searchType = SearchType.IS;
        }
        switch(searchType) {
            case CONTAINS:
                return (root, query, builder) -> builder.like(root.get(field), contains(expression));
            case BEGINS:
                return (root, query, builder) -> builder.like(root.get(field), startWith(expression));
            case ENDS:
                return (root, query, builder) -> builder.like(root.get(field), endWith(expression));
            default:
                return (root, query, builder) -> builder.equal(root.get(field), expression);
        }
    }

    public static Specification<Customer> getNumberSpecification(String expression, String field, SearchType searchType) {
        if(searchType == null) {
            searchType = SearchType.IS;
        }
        switch(searchType) {
            case BETWEEN:
                return (root, query, builder) -> builder.like(root.get(field), contains(expression));
            case LESS_THAN:
                return (root, query, builder) -> builder.like(root.get(field), startWith(expression));
            case MORE_THAN:
                return (root, query, builder) -> builder.like(root.get(field), endWith(expression));
            default:
                return (root, query, builder) -> builder.equal(root.get(field), expression);
        }
    }

    public static Specification<Customer> getDateSpecification(String expression, String field, SearchType searchType) {
        if(searchType == null) {
            searchType = SearchType.IS;
        }
        switch(searchType) {
            case BETWEEN:
                return (root, query, builder) -> builder.like(root.get(field), contains(expression));
            case BEFORE:
                return (root, query, builder) -> builder.like(root.get(field), startWith(expression));
            case AFTER:
                return (root, query, builder) -> builder.like(root.get(field), endWith(expression));
            default:
                return (root, query, builder) -> builder.equal(root.get(field), expression);
        }
    }

    private static String contains(String expression) {
        return MessageFormat.format("%{0}%", expression);
    }

    private static String startWith(String expression) {
        return MessageFormat.format("%{0}", expression);
    }

    private static String endWith(String expression) {
        return MessageFormat.format("{0}%", expression);
    }
}
