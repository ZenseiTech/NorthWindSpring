package com.zenseitech.northwind.util;

public class DateUtil {
    public static String transform(String inputDate) {
        String [] values = inputDate.split("/");
        StringBuilder sb = new StringBuilder();
        sb.append(values[2]).append("-").append(values[0]).append("-").append(values[1]);
        return sb.toString();
    }
}
