create table categoria
(
    id   bigint      not null,
    nome varchar(50) not null
);

alter table categoria
    add primary key (id);

alter table produto
    add column categoria_id bigint;

alter table produto
    add foreign key (categoria_id) references categoria;