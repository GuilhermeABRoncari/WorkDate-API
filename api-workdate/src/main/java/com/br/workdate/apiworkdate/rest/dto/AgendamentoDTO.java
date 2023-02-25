package com.br.workdate.apiworkdate.rest.dto;

import com.br.workdate.apiworkdate.domain.entity.Agendamento;
import com.br.workdate.apiworkdate.domain.entity.Cliente;
import com.br.workdate.apiworkdate.domain.entity.Servico;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

public record AgendamentoDTO(
        Cliente cliente,
        Servico servico,
        @NotNull
        Timestamp horario) {
    public AgendamentoDTO(Agendamento agendamento) {
        this(agendamento.getCliente(), agendamento.getServico(), agendamento.getHorario());
    }
}
