drop table agendamentos;

alter table clientes rename column id to cliente_id;
alter table servicos rename column id to servico_id;

create table agendamentos (
        agendamento_id bigint not null auto_increment,
        cliente_id bigint not null,
        servico_id bigint not null,
        horario timestamp not null unique,

        primary key(agendamento_id),
        key cliente_fk (cliente_id),
        constraint cliente_fk foreign key (cliente_id) references clientes (cliente_id),
        key servico_fk (servico_id),
        constraint servico_fk foreign key (servico_id) references servicos (servico_id)
);
