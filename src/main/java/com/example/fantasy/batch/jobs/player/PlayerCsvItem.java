package com.example.fantasy.batch.jobs.player;

import com.example.fantasy.core.batch.common.CsvItem;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

/**
 * Data class representing a player record from CSV import files.
 * Contains all fields that might be present in player CSV files.
 */
@Data
@NoArgsConstructor
public class PlayerCsvItem implements CsvItem {

    private String name;
    private String position;
    private String club;
    // Add other relevant fields as needed

    /**
     * Validates that the minimum required fields are present and valid
     *
     * @return true if the record contains valid data, false otherwise
     */
    @Override
    public boolean isValid() {
        // TODO: Implement validation logic
        // Example validation - requiring name and position
        return StringUtils.hasText(name) && StringUtils.hasText(position);
    }
}
