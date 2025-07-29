-- V0.1__create_positions_table.sql

CREATE TABLE position (
  id  SMALLINT      PRIMARY KEY,
  position        VARCHAR(10)   NOT NULL UNIQUE,
  description     VARCHAR(50)   NOT NULL
);

INSERT INTO position (id, position, description)
VALUES
  (1,  'GK',  'Goalkeeper'),
  (2,  'DL',  'Left Defender'),
  (3,  'DC',  'Center Back'),
  (4,  'DR',  'Right Defender'),
  (5,  'WBL', 'Wing Back Left'),
  (6,  'WBR', 'Wing Back Right'),
  (7,  'DM',  'Defensive Mid'),
  (8,  'MC',  'Central Mid'),
  (9,  'ML',  'Midfield Left'),
  (10, 'MR',  'Midfield Right'),
  (11, 'AML', 'Attacking Mid Left'),
  (12, 'AMC', 'Attacking Mid Center'),
  (13, 'AMR', 'Attacking Mid Right'),
  (14, 'SC',  'Striker Center');
