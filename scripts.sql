drop sequence if exists loja_seq;
drop table if exists endereco cascade;
drop table if exists usuario cascade;
drop table if exists produto cascade;
drop table if exists produto_pedido cascade;
drop table if exists pedido cascade;
drop table if exists categoria cascade;

create sequence loja_seq start 1;

create table endereco
(
    id          bigint primary key,
    rua         varchar(100) not null,
    numero      int          not null,
    complemento varchar(100),
    cidade      varchar(30)  not null,
    estado      varchar(30)  not null,
    cep         varchar(8)   not null
);

create table usuario
(
    cpf             varchar(14) primary key,
    nome            varchar(20)  not null,
    sobrenome       varchar(20)  not null,
    email           varchar(100) not null unique,
    senha           varchar(300) not null,
    telefone        varchar(11)  not null,
    data_nascimento date,
    tipo            int          not null,
    endereco        bigint       not null references endereco (id)
);

create table produto
(
    id        varchar(100) not null primary key,
    nome      varchar(100) not null,
    descricao varchar(500) not null,
    preco     varchar(13)  not null,
    imagem    varchar(200) not null
);

create table pedido
(
    id          bigint      not null primary key,
    usuario     varchar(14) not null references usuario (cpf),
    valor_total varchar(13) not null,
    status      int         not null
);

create table produto_pedido
(
    pedido     bigint       not null references pedido (id),
    produto    varchar(100) not null references produto (id),
    quantidade int          not null,
    valorTotal varchar(13)  not null
);

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