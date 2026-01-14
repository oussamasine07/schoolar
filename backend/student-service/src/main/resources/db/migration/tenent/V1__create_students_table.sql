CREATE TABLE students
(
    id             SERIAL PRIMARY KEY,
    ref            VARCHAR(255),
    fistname       VARCHAR(255) NOT NULL,
    lastname       VARCHAR(255) NOT NULL,
    date_of_birth  date         NOT NULL,
    city_of_birth  VARCHAR(255) NOT NULL,
    city_of_living VARCHAR(255) NOT NULL,
    address        VARCHAR(255) NOT NULL
);