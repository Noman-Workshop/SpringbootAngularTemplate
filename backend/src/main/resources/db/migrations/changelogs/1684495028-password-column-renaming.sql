-- liquibase formatted sql

-- changeset eastnetic:1684495028-1
alter table users
    rename column hashed_password to "password";