create table agendamentos (
        id bigint not null auto_increment,
        horario timestamp not null unique,

        primary key(id)
);