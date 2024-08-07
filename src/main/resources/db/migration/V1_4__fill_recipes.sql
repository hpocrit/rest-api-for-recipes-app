drop table if exists recipes cascade;

create table recipes
(
    id   bigserial
        constraint recipes_pk
            primary key,
    title varchar(80) not null,
    image varchar(255) not null
);

insert into recipes (title, image)
values ('Oatmeal with fruits', 'http://vsegda-pomnim.com/uploads/posts/2022-04/1651258078_25-vsegda-pomnim-com-p-zavtrak-kasha-s-yagodami-foto-30.jpg'),
       ('Steak', 'https://mykaleidoscope.ru/x/uploads/posts/2022-09/1663671165_50-mykaleidoscope-ru-p-tenderloin-steik-yeda-vkontakte-55.jpg'),
       ('Chicken soup', 'https://recept-borscha.ru/wp-content/uploads/5/3/b/53b0517c59dd9833627e982e2598ce41.jpeg');