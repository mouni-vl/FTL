-- V0.2_create_clubs-table.sql

CREATE TABLE club (
                      id BIGINT NOT NULL PRIMARY KEY,
                      fm_id INTEGER UNIQUE NOT NULL,
                      name VARCHAR(255) NOT NULL,
                      short_name VARCHAR(255),
                      three_letter_name VARCHAR(10),
                      logo_url                VARCHAR(255),
                      year_founded INTEGER,
                      footballing_nation VARCHAR(255),
                      reputation INTEGER,
                      likely_finishing_group VARCHAR(255),
                      captain_id BIGINT REFERENCES player(id),
                      vice_captain_id BIGINT REFERENCES player(id),
                      ca16 INTEGER,
                      official_hashtag VARCHAR(100),
                      nickname VARCHAR(100),
                      stadium_id BIGINT REFERENCES stadium(id),
                      -- Audit columns (inherited from BaseEntity)
                      created_at          TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
                      updated_at          TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
                      created_by          VARCHAR(50) NULL,
                      updated_by          VARCHAR(50) NULL
);

CREATE TABLE stadium (
                         id BIGINT NOT NULL PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         capacity INTEGER
);
