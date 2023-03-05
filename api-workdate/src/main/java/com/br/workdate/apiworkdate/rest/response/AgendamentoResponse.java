package com.br.workdate.apiworkdate.rest.response;

import com.br.workdate.apiworkdate.domain.entity.Agendamento;
import com.br.workdate.apiworkdate.domain.entity.Cliente;
import com.br.workdate.apiworkdate.domain.entity.Servico;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.sql.Timestamp;

public record AgendamentoResponse(
        Long id,
        Cliente cliente,
        Servico servico,
        Timestamp horario) {
    public AgendamentoResponse(Agendamento agendamento) {
        this(agendamento.getId(), agendamento.getCliente(), agendamento.getServico(), agendamento.getHorario());
    }
}
