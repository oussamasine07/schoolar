CREATE TABLE tn_american_school.class_rooms (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE tn_american_school.level_of_education (
   id SERIAL PRIMARY KEY,
   name VARCHAR(255) NOT NULL,
   educational_stage VARCHAR(255) NOT NULL
);

CREATE TABLE tn_american_school.school_year (
    id SERIAL PRIMARY KEY,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    is_closed BOOLEAN NOT NULL DEFAULT FALSE
);
