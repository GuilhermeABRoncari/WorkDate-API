package com.br.workdate.apiworkdate.domain.agendamentos;

import com.br.workdate.apiworkdate.domain.clientes.Cliente;
import com.br.workdate.apiworkdate.domain.servicos.Servicos;

import java.sql.Timestamp;

public record AgendamentoData(Cliente cliente, Servicos servico, Timestamp horario) {
    public AgendamentoData(Agendamento agendamento){
        this(agendamento.getCliente(), agendamento.getServico(), agendamento.getHorario());
    }
}
