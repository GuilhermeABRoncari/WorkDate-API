package com.br.workdate.apiworkdate.rest.dto;

import com.br.workdate.apiworkdate.domain.entity.Agendamento;
import com.br.workdate.apiworkdate.domain.entity.Cliente;
import com.br.workdate.apiworkdate.domain.entity.Servico;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

public record AgendamentoDTO(
        Long cliente_id,
        Long servico_id,
        @NotNull
        @Future
        Timestamp horario) {
    public AgendamentoDTO(Agendamento agendamento) {
        this(agendamento.getCliente().getId(), agendamento.getServico().getId(), agendamento.getHorario());
    }
}
