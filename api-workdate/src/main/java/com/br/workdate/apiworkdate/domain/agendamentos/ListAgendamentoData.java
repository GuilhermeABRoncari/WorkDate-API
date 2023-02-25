package com.br.workdate.apiworkdate.domain.agendamentos;

import com.br.workdate.apiworkdate.domain.entity.Agendamento;
import com.br.workdate.apiworkdate.domain.entity.Cliente;
import com.br.workdate.apiworkdate.domain.entity.Servico;

import java.sql.Timestamp;

public record ListAgendamentoData(
        Long id,
        Cliente cliente,
        Servico servico,
        Timestamp horario) {
    public ListAgendamentoData(Agendamento agendamento){
        this(agendamento.getId(), agendamento.getCliente(), agendamento.getServico(), agendamento.getHorario());
    }
}
