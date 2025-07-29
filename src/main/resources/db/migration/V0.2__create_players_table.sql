-- V0.2__create_players_table.sql

CREATE TABLE player
(
    id                  BIGINT AUTO_INCREMENT PRIMARY KEY,

    fm_id               INTEGER      NOT NULL UNIQUE,              -- “FM ID”
    first_name          VARCHAR(50)  NOT NULL,                     -- “First Name”
    second_name         VARCHAR(50)  NOT NULL,                     -- “Second Name”
    full_name           VARCHAR(100) NOT NULL,                     -- “Name”
    photo_url            VARCHAR(255),
    date_of_birth       DATE         NOT NULL,                     -- “Date Of Birth”
    city_of_birth       VARCHAR(50) NULL,                          -- “City Of Birth”

    permanent_club      VARCHAR(100) NULL,                         -- “Person > Permanent Club”
    based_club          VARCHAR(100) NULL,                         -- “Person > Based Club”
    loan_club           VARCHAR(100) NULL,                         -- “Person > Loan Club”
    squad_number        SMALLINT NULL,                             -- “PersonClubContract > Squad Number”

    nationality_1       VARCHAR(50) NULL,                          -- “Person > Nationality”
    nationality_2       VARCHAR(50) NULL,                          -- “Person > Second Nationality”
    nationality_3       VARCHAR(50) NULL,                          -- “Person > Third Nationality”

    current_ability     SMALLINT NULL,                             -- “PlayerAttribute > Current Ability”
    potential_ability   SMALLINT NULL,                             -- “PlayerAttribute > Potential Ability”
    left_foot           SMALLINT NULL,                             -- “PlayerAttribute > Left Foot”
    right_foot          SMALLINT NULL,                             -- “PlayerAttribute > Right Foot”

    availability_status VARCHAR(20)  NOT NULL DEFAULT 'available', -- “availability” (e.g. available/injured/suspended)

    -- Audit columns (inherited from BaseEntity)
    created_at          TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at          TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by          VARCHAR(50) NULL,                          -- e.g. username or system user that inserted
    updated_by          VARCHAR(50) NULL,                          -- e.g. username that last modified

    nfe                 BOOLEAN               DEFAULT FALSE,       -- “Person > NFE”
    deleted             BOOLEAN               DEFAULT FALSE        -- “Is person deleted”
);