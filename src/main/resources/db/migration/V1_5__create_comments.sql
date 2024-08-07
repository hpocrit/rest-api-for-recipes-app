drop table if exists comments cascade;

create table comments
(
    id   bigserial
        constraint comments_pk
            primary key,
    recipe_id  bigserial   not null
        constraint comments_recipes_id_fk
            references recipes
            on update cascade on delete cascade,
    value varchar(255) not null,
    rating float not null
);