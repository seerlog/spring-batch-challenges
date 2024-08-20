package com.example.springbatchchallenges.utils;

import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Util {
    public static LocalDate parseDate(String date) {
        return ObjectUtils.isEmpty(date) ? null : LocalDate.parse(date);
    }

    public static LocalDateTime parseDateTime(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return ObjectUtils.isEmpty(dateTime) ? null : LocalDateTime.parse(dateTime, formatter);
    }

    public static Integer parseInt(String value) {
        return ObjectUtils.isEmpty(value) ? 0 : Integer.parseInt(value);
    }

    public static Double parseDouble(String value) {
        return ObjectUtils.isEmpty(value) ? 0 : Double.parseDouble(value);
    }
}
