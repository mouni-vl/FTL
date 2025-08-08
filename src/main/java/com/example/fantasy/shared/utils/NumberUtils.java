package com.example.fantasy.shared.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.util.StringUtils;

@Slf4j
public final class  NumberUtils {
    private NumberUtils() {}

    public static Integer safeReadInt(FieldSet fs, String column) {
        try {
            String value = fs.readString(column);
            return (value == null || value.isBlank()) ? null : Integer.valueOf(value);
        } catch (NumberFormatException e) {
            log.warn("Invalid integer in column {}: '{}'", column, fs.readString(column));
            return null;
        }
    }

    public static Long safeReadLong(FieldSet fs, String column) {
        try {
            String value = fs.readString(column);
            return (value == null || value.isBlank()) ? null : Long.valueOf(value);
        } catch (NumberFormatException e) {
            log.warn("Invalid long in column {}: '{}'", column, fs.readString(column));
            return null;
        }
    }

    public static Integer parseInteger(String value) {
        if (!org.springframework.util.StringUtils.hasText(value)) {
            return null;
        }
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Double parseDouble(String value) {
        if (!StringUtils.hasText(value)) {
            return null;
        }
        try {
            return Double.parseDouble(value.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Boolean parseBoolean(Integer value) {
        if (value == null) {
            return false;
        }
        return value == 1;
    }
}
