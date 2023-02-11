package com.br.workdate.apiworkdate.domain.agendamentos;

import com.br.workdate.apiworkdate.domain.clientes.Cliente;
import com.br.workdate.apiworkdate.domain.servicos.Servicos;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

public record ListAgendamentoData(
        Long id,
        Cliente cliente,
        Servicos servico,
        Timestamp horario) {
    public ListAgendamentoData(Agendamento agendamento){
        this(agendamento.getId(), agendamento.getCliente(), agendamento.getServico(), agendamento.getHorario());
    }
}
