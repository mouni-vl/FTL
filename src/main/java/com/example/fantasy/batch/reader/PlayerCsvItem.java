package com.example.fantasy.batch.reader;

import lombok.Data;

/**
 * Represents one row in the FM CSV: a player + a single position+rating.
 */
@Data
public class PlayerCsvItem {
    private String externalPlayerId;
    private String firstName;
    private String lastName;
    private String position;
    private Integer rating;
    private String clubId;
}
