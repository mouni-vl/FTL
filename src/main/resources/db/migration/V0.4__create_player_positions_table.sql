-- V0.4__create_player_positions_table.sql

CREATE TABLE player_position
(
    player_id     BIGINT        NOT NULL PRIMARY KEY, -- FK + PK = one-to-one
    main_position VARCHAR(3) NOT NULL CHECK (main_position IN ('GK', 'DEF', 'MID', 'FWD')),

    -- Positional ratings (0 to 20 scale)
    gk            TINYINT    NOT NULL DEFAULT 0 CHECK (gk BETWEEN 0 AND 20),
    dl            TINYINT    NOT NULL DEFAULT 0 CHECK (dl BETWEEN 0 AND 20),
    dc            TINYINT    NOT NULL DEFAULT 0 CHECK (dc BETWEEN 0 AND 20),
    dr            TINYINT    NOT NULL DEFAULT 0 CHECK (dr BETWEEN 0 AND 20),
    wbl           TINYINT    NOT NULL DEFAULT 0 CHECK (wbl BETWEEN 0 AND 20),
    wbr           TINYINT    NOT NULL DEFAULT 0 CHECK (wbr BETWEEN 0 AND 20),
    dm            TINYINT    NOT NULL DEFAULT 0 CHECK (dm BETWEEN 0 AND 20),
    mc            TINYINT    NOT NULL DEFAULT 0 CHECK (mc BETWEEN 0 AND 20),
    ml            TINYINT    NOT NULL DEFAULT 0 CHECK (ml BETWEEN 0 AND 20),
    mr            TINYINT    NOT NULL DEFAULT 0 CHECK (mr BETWEEN 0 AND 20),
    aml           TINYINT    NOT NULL DEFAULT 0 CHECK (aml BETWEEN 0 AND 20),
    amc           TINYINT    NOT NULL DEFAULT 0 CHECK (amc BETWEEN 0 AND 20),
    amr           TINYINT    NOT NULL DEFAULT 0 CHECK (amr BETWEEN 0 AND 20),
    sc            TINYINT    NOT NULL DEFAULT 0 CHECK (sc BETWEEN 0 AND 20),

    CONSTRAINT fk_player_positions_player
        FOREIGN KEY (player_id)
            REFERENCES player (id)
            ON DELETE CASCADE
);

-- public class PlayerPositionDto {
--     private Integer playerId;
--     private String  mainPosition;
--     private Map<String,Integer> ratings;   // "GK"→18, "DL"→5, …
-- }