drop table if exists recipes cascade;

create table recipes
(
    id   bigserial
        constraint recipes_pk
            primary key,
    title varchar(80) not null,
    image varchar(255) not null
);