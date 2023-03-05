package com.br.workdate.apiworkdate.rest.dto;

import com.br.workdate.apiworkdate.domain.entity.Agendamento;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

public record AgendamentoDTO(
        @JsonIgnore
        Long id,
        @NotNull(message = "{message-01}")
        Long cliente_id,
        @NotNull(message = "{message-02}")
        Long servico_id,
        @NotNull
        @Future
        Timestamp horario,
        String observacoes
        ) {
    public AgendamentoDTO(Agendamento agendamento) {
        this(agendamento.getId(), agendamento.getCliente().getId(), agendamento.getServico().getId(), agendamento.getHorario(), agendamento.getObservacoes());
    }
}
