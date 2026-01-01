SET search_path TO tn_american_school;

create table class_rooms (
    id serial primary key,
    name varchar(255) not null
);

create table level_of_education (
    id serial primary key,
    name varchar(255) not null,
    educational_stage varchar(255) not null
);

create table school_year (
    id serial primary key,
    start date not null,
    end date not null,
    is_closed boolean not null default false
);