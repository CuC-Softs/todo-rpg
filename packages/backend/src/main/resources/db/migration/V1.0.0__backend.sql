create sequence hibernate_sequence start 1 increment 1;

create table users
(
    id         int8         not null,
    created_at timestamp    not null,
    updated_at timestamp    not null,
    email      varchar(255) not null,
    keycloakId varchar(255) not null,
    name       varchar(255) not null,
    primary key (id)
);
