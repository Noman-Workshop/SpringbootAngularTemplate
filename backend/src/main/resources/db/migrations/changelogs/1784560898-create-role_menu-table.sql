-- liquibase formatted sql

-- changeset eastnetic:1784560898-1
DROP TABLE IF EXISTS role_menu;

-- changeset eastnetic:1784560898-2
CREATE TABLE role_menu (
    id              bigint generated by default as identity not null,
    role_id    bigint references roles(id),
    menu_id    bigint references menu(id),
    created_at timestamp not null,
    updated_at timestamp not null,
    constraint pk_role_menu primary key (id),
    constraint unique_role_menu unique (role_id,menu_id)
);

