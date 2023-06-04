create table roles
(
    id              bigint generated by default as identity not null,
    name           varchar(255)                            not null,
    created_at  timestamp without time zone             not null,
    updated_at  timestamp without time zone,
    constraint pk_roles primary key (id)
);