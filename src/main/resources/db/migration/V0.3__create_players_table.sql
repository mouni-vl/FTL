-- V0.3__create_players_table.sql

CREATE TABLE player
(
    id                  BIGINT AUTO_INCREMENT PRIMARY KEY,

    first_name          VARCHAR(50)  NOT NULL,                     -- “First Name”
    second_name         VARCHAR(50)  NOT NULL,                     -- “Second Name”
    full_name           VARCHAR(100) NULL,                         -- “Name”
    photo_url           VARCHAR(255) NULL,                         -- “Photo URL”
    date_of_birth       DATE         NULL,                         -- “Date Of Birth”
    year_of_birth       SMALLINT      NULL,                        -- “Year Of Birth”
    city_of_birth       VARCHAR(50) NULL,                          -- “City Of Birth”

    permanent_club_id   BIGINT NULL,                               -- “Person > Permanent Club”
    based_club_id       BIGINT NULL,                               -- “Person > Based Club”
    loan_club_id        BIGINT NULL,                               -- “Person > Loan Club”
    squad_number        SMALLINT NULL,                             -- “PersonClubContract > Squad Number”

    nationality_1       BIGINT,                                    -- “Person > Nationality”
    nationality_2       BIGINT NULL,                               -- “Person > Second Nationality”
    nationality_3       BIGINT NULL,                               -- “Person > Third Nationality”

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

    -- Foreign key constraints for clubs
    CONSTRAINT fk_permanent_club FOREIGN KEY (permanent_club_id) REFERENCES club(id),
    CONSTRAINT fk_based_club     FOREIGN KEY (based_club_id)     REFERENCES club(id),
    CONSTRAINT fk_loan_club      FOREIGN KEY (loan_club_id)      REFERENCES club(id)
);
