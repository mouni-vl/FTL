package com.example.fantasy.core.batch.common;

/**
 * Base interface for all CSV item types used in batch processing.
 * All CSV record classes should implement this interface to ensure
 * they can be properly validated before processing.
 */
public interface CsvItem {

    /**
     * Validates the CSV record data.
     *
     * @return true if the data is valid, false otherwise
     */
    boolean isValid();
}
