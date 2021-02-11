package com.zenseitech.northwind.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.List;

@Getter
@Setter
@ToString
public class Search {
    private String field;
    private String type;
    private Object value;
    private String operator;

    public static Specification<Object> getStringSpecification(String expression, String field, SearchType searchType) {
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

    public static Specification<Object> getIntegerSpecification(List<Object> expressions, String field, SearchType searchType) {
        if(searchType == null) {
            searchType = SearchType.IS;
        }

        final Integer expression = (Integer) expressions.get(0);

        switch(searchType) {
            case BETWEEN:
                final Integer expression2 = (Integer) expressions.get(1);
                return (root, query, builder) -> builder.between(root.get(field), expression, expression2);
            case LESS_THAN:
                return (root, query, builder) -> builder.lessThan(root.get(field), expression);
            case MORE_THAN:
                return (root, query, builder) -> builder.greaterThan(root.get(field), expression);
            default:
                return (root, query, builder) -> builder.equal(root.get(field), expression);
        }
    }

    public static Specification<Object> getDateSpecification(String expression, String field, SearchType searchType) {
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
