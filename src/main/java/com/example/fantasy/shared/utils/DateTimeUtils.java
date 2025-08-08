package com.example.fantasy.shared.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Slf4j
public final class DateTimeUtils {
        private DateTimeUtils() {}

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static LocalDate parseDate(String dateString) {
        if (!StringUtils.hasText(dateString)) {
            return null;
        }
        try {
            return LocalDate.parse(dateString, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            log.warn("Invalid date format: {}", dateString);
            return null;
        }
    }
    public static Instant nowUtc() {
        return Instant.now();
    }

    public static LocalDate atZone(Instant instant, ZoneId zone) {
        return instant.atZone(zone).toLocalDate();
    }
}
