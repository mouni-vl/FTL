package com.example.fantasy.shared.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

/**
 * Utility class for date and time operations
 */
public final class DateTimeUtils {
    
    private DateTimeUtils() {
        // Private constructor to prevent instantiation
    }
    
    /**
     * Get current UTC time as Instant
     * @return current UTC time
     */
    public static Instant nowUtc() {
        return Instant.now();
    }
    
    /**
     * Convert Instant to LocalDate in the specified time zone
     * @param instant the instant to convert
     * @param zone the time zone
     * @return the LocalDate in the specified time zone
     */
    public static LocalDate atZone(Instant instant, ZoneId zone) {
        return instant.atZone(zone).toLocalDate();
    }
}
