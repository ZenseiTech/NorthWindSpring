package com.zenseitech.northwind.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.Specification;

import java.text.MessageFormat;
import java.util.Date;
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

        if(field == null) {
            return null;
        }

        if(searchType == null) {
            throw new RuntimeException("SearchType cannot be null for field: [" + field + "]");
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

    public static Specification<Object> getIntegerSpecification(List<Integer> expressions, String field, SearchType searchType) {

        if(field == null) {
            return null;
        }

        if(searchType == null) {
            throw new RuntimeException("SearchType cannot be null for field: [" + field + "]");
        }

        final Integer expression = expressions.get(0);

        switch(searchType) {
            case BETWEEN:
                final Integer expression2 = expressions.get(1);
                return (root, query, builder) -> builder.between(root.get(field), expression, expression2);
            case LESS_THAN:
                return (root, query, builder) -> builder.lessThan(root.get(field), expression);
            case MORE_THAN:
                return (root, query, builder) -> builder.greaterThan(root.get(field), expression);
            default:
                return (root, query, builder) -> builder.equal(root.get(field), expression);
        }
    }

    public static Specification<Object> getDateSpecification(List<Date> expressions, String field, SearchType searchType) {

        if(field == null) {
            return null;
        }

        final Date expression = expressions.get(0);

        if(searchType == null) {
            throw new RuntimeException("SearchType cannot be null for field: [" + field + "]");
        }

        switch(searchType) {
            case BETWEEN:
                final Date expression2 = expressions.get(1);
                return (root, query, builder) -> builder.between(root.get(field), expression, expression2);
            case BEFORE:
                return (root, query, builder) -> builder.lessThan(root.get(field), expression);
            case AFTER:
                return (root, query, builder) -> builder.greaterThan(root.get(field), expression);
            default:
                return (root, query, builder) -> builder.equal(root.get(field), expression);
        }
    }

    private static String contains(String expression) {
        return MessageFormat.format("%{0}%", expression);
    }

    private static String startWith(String expression) {
        return MessageFormat.format("{0}%", expression);
    }

    private static String endWith(String expression) {
        return MessageFormat.format("%{0}", expression);
    }
}
