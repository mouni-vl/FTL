-- V0.2_create_clubs-table.sql

CREATE TABLE club (
                      id                          INTEGER         PRIMARY KEY,

    -- Identifiers
                      name                        VARCHAR(255)    NOT NULL,
                      short_name                  VARCHAR(255),
                      six_letter_name             VARCHAR(10),
                      three_letter_name           VARCHAR(10),
                      alt_three_letter_name       VARCHAR(10),

    -- Identity & Metadata
                      nickname                    VARCHAR(100),
                      official_hashtag            VARCHAR(100),
                      logo_url                    VARCHAR(255),
                      year_founded                SMALLINT,

    -- Location
                      footballing_nation          BIGINT NULL,
                      country                     BIGINT,
                      city                        BIGINT NULL,

    -- Competition & Reputation
                      division_id                 BIGINT,
                      reputation                  SMALLINT,
                      likely_finishing_group      VARCHAR(255),

    -- Players
                      captain_id                  BIGINT         /*REFERENCES player(id)*/,
                      vice_captain_id             BIGINT         /*REFERENCES player(id)*/,

    -- Technical
                      ca16                        SMALLINT,
                      primary_color               VARCHAR(10),
                      secondary_color             VARCHAR(10),

                      extinct                     BOOLEAN         DEFAULT FALSE,
                      nfe                         BOOLEAN         DEFAULT FALSE,

    -- Stadium
                      stadium_id                  BIGINT         REFERENCES stadium(id),

    -- Audit
                      created_at                  TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
                      updated_at                  TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
                      created_by                  VARCHAR(50),
                      updated_by                  VARCHAR(50)
);

CREATE TABLE stadium (
                         id              INTEGER       PRIMARY KEY,
                         name            VARCHAR(255)  NOT NULL,
                         capacity        INTEGER
);

