-- liquibase formatted sql

-- changeset eastnetic:1684060693-1
alter table todos
    add column "is_important" boolean not null default false;
