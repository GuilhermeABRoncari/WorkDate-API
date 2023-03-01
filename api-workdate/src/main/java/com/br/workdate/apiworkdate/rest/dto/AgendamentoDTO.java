package com.br.workdate.apiworkdate.rest.dto;

import com.br.workdate.apiworkdate.domain.entity.Agendamento;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

public record AgendamentoDTO(
        @NotNull
        Long cliente_id,
        @NotNull
        Long servico_id,
        @NotNull
        @Future
        Timestamp horario,
        String observacoes
        ) {
    public AgendamentoDTO(Agendamento agendamento) {
        this(agendamento.getCliente().getId(), agendamento.getServico().getId(), agendamento.getHorario(), agendamento.getObservacoes());
    }
}
