package com.licnosoft.linkedin.util;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateUtil {

    private DateUtil() {
    }

    public static LocalDate toStartOfDate(LocalDate date) {
        return (date != null) ? date.atStartOfDay().toLocalDate() : null;
    }

    public static LocalDate toEndOfDate(LocalDate date) {
        return (date != null) ? date.atTime(23, 59).toLocalDate() : null;
    }

    public static LocalDateTime toStartOfDay(LocalDate date) {
        return (date != null) ? date.atStartOfDay() : null;
    }

    public static LocalDateTime toEndOfDay(LocalDate date) {
        return (date != null) ? date.atTime(23, 59) : null;
    }

}
