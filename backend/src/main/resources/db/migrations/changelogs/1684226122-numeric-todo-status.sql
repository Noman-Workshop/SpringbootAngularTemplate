-- liquibase formatted sql

-- changeset eastnetic:1684226122-1
alter table todos
    add column numeric_status integer not null default 101;

-- changeset eastnetic:1684226122-2
update todos
set numeric_status = 101
where status = 'todo';

-- changeset eastnetic:1684226122-3
update todos
set numeric_status = 102
where status = 'doing';

-- changeset eastnetic:1684226122-4
update todos
set numeric_status = 201
where status = 'done';

-- changeset eastnetic:1684226122-5
alter table todos
    drop column status;

-- changeset eastnetic:1684226122-6
alter table todos
    rename column numeric_status to status;