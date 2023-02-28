create table lancamentos_financeiros (
        lancamentos_financeiros_id bigint not null auto_increment,
        agendamento_id bigint not null,
        situation varchar(4) not null,

        primary key(lancamentos_financeiros_id),
        key agendamento_fk (agendamento_id),
        constraint agendamento_fk foreign key (agendamento_id) references agendamentos (agendamento_id)
);