package com.br.workdate.apiworkdate.rest.response;

import com.br.workdate.apiworkdate.domain.entity.Agendamento;
import com.br.workdate.apiworkdate.domain.entity.Cliente;
import com.br.workdate.apiworkdate.domain.entity.Servico;

import java.sql.Timestamp;

public record AgendamentoResponse(
        Long id,
        Cliente cliente,
        Servico servico,
        Timestamp horario,
        String observacao) {
    public AgendamentoResponse(Agendamento agendamento) {
        this(agendamento.getId(), agendamento.getCliente(), agendamento.getServico(), agendamento.getHorario(), agendamento.getObservacoes());
    }
}
